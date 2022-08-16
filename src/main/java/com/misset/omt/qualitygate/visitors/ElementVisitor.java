package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.parser.OMTParser;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.yaml.snakeyaml.nodes.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract ElementVisitor that will parse the entire OMT element tree and call visit(T element) on the
 * elements of the extended implementation.
 */
public abstract class ElementVisitor<T extends OMTElement> {
    private static final Logger LOGGER = Loggers.get(ElementVisitor.class);

    private static final OMTParser parser = new OMTParser();

    protected abstract Class<T> getOMTElementClass();

    protected SensorContext context;

    protected InputFile inputFile;

    public void visitElements(SensorContext context, InputFile file) {
        try {
            this.context = context;
            this.inputFile = file;
            LOGGER.debug("Great, we're going to visit OMT elements at " + file.filename());
            OMTFileType fileType = parser.getFileType(file.filename());
            LOGGER.debug(fileType.name() + " is the filetype for " + file.filename());
            OMTFile omtFile = parser.process(file.contents(), fileType);
            Collection<OMTElement> allElements = new ArrayList<>(TreeUtil.getAllChildren(omtFile));
            allElements.add(omtFile);
            allElements
                    .stream()
                    .filter(getOMTElementClass()::isInstance)
                    .map(getOMTElementClass()::cast)
                    .forEach(this::visit);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    abstract void visit(T element);

    protected boolean isActive(RuleKey key) {
        return context.activeRules().find(key) != null;
    }

    protected String getProperty(RuleKey key, String propertyKey) {
        ActiveRule activeRule = context.activeRules().find(key);
        if(activeRule == null) {
            return null;
        }
        return activeRule.param(propertyKey);
    }

    /**
     * Most basic method to add a new issue to the sensor
     * Extracts location information from the Yaml node and respects the configured severity set by the Rule
     */
    protected void newIssue(RuleKey key, OMTElement element) {
        NewIssue newIssue = context.newIssue();
        newIssue.forRule(key);
        NewIssueLocation newIssueLocation = newIssue.newLocation();

        newIssueLocation.on(inputFile);
        newIssueLocation.at(getTextRange(inputFile, element.getNode()));
        newIssue.at(newIssueLocation);
        newIssue.save();
    }

    /**
     * Translates the Yaml node to a TextRange for the Sonarqube Sensor
     */
    protected TextRange getTextRange(InputFile inputFile, Node node) {
        // SnakeYAML uses 0-based line counting
        // Sonarqube uses 1-based line counting
        return inputFile.newRange(
                node.getStartMark().getLine() + 1,
                node.getStartMark().getColumn(),
                node.getEndMark().getLine() + 1,
                node.getEndMark().getColumn()
        );
    }
}
