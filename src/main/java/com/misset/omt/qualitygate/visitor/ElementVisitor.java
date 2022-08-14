package com.misset.omt.qualitygate.visitor;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.parser.OMTParser;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;
import org.yaml.snakeyaml.nodes.Node;

import java.io.IOException;

public abstract class ElementVisitor {

    private static final OMTParser parser = new OMTParser();

    protected SensorContext context;

    private InputFile file;

    public void visitElements(SensorContext context, InputFile file) {
        try {
            this.context = context;
            this.file = file;
            OMTFile omtFile = parser.process(file.contents(), parser.getFileType(file.filename()));
            TreeUtil.getAllChildren(omtFile).forEach(this::visit);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    abstract void visit(OMTElement element);

    protected boolean isActive(RuleKey key) {
        return context.activeRules().find(key) != null;
    }

    /**
     * Most basic method to add a new issue to the sensor
     * Extracts location information from the Yaml node and respects the configured severity set by the Rule
     */
    protected void newIssue(RuleKey key, OMTElement element) {
        NewIssue newIssue = context.newIssue();
        newIssue.forRule(key);
        NewIssueLocation newIssueLocation = newIssue.newLocation();

        newIssueLocation.on(file);
        newIssueLocation.at(getTextRange(file, element.getNode()));
    }

    /**
     * Translates the Yaml node to a TextRange for the Sonarqube Sensor
     */
    protected TextRange getTextRange(InputFile inputFile, Node node) {
        return inputFile.newRange(
                node.getStartMark().getLine(),
                node.getStartMark().getColumn(),
                node.getEndMark().getLine(),
                node.getEndMark().getColumn()
        );
    }
}
