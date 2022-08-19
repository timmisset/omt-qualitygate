package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.issue.OMTIssue;
import com.misset.omt.qualitygate.issue.OMTIssueLocation;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.parser.OMTParser;
import com.misset.omt.qualitygate.sensor.OMTSensorContext;
import com.misset.omt.qualitygate.validators.Validator;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.SequenceNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract AbstractElementVisitor that will parse the entire OMT element tree and call visit(T element) on the
 * elements of the extended implementation.
 */
public abstract class AbstractElementVisitor<T extends OMTElement> implements ElementVisitor<T> {
    private static final Logger LOGGER = Loggers.get(AbstractElementVisitor.class);
    private static final OMTParser parser = new OMTParser();
    private final Class<T> elementClass;

    protected OMTSensorContext context;

    private T currentElement;

    protected AbstractElementVisitor(Class<T> elementClass) {
        this.elementClass = elementClass;
    }


    public final void visitElements(OMTSensorContext context) {
        this.context = context;
        LOGGER.debug("Great, we're going to visit OMT elements at " + context.getFilename());
        OMTFileType fileType = parser.getFileType(context.getFilename());
        LOGGER.debug(fileType.name() + " is the filetype for " + context.getFilename());
        OMTFile omtFile = parser.process(context.getContent(), fileType);
        Collection<OMTElement> allElements = new ArrayList<>(TreeUtil.getAllChildren(omtFile));
        allElements.add(omtFile);
        allElements
                .stream()
                .filter(elementClass::isInstance)
                .map(elementClass::cast)
                .forEach(this::visitElement);

    }

    private void visitElement(T element) {
        currentElement = element;
        visit(element);
    }

    abstract void visit(T element);

    protected boolean isActive(RuleKey key) {
        return context.getActiveRules().find(key) != null;
    }

    protected void validateIfActive(RuleKey key, Validator<T> validator) {
        if(isActive(key)) {
            validator.validate(currentElement);
        }
    }

    public String getProperty(RuleKey key, String propertyKey) {
        ActiveRule activeRule = context.getActiveRules().find(key);
        if(activeRule == null) {
            return null;
        }
        return activeRule.param(propertyKey);
    }

    /**
     * Most basic method to add a new issue to the sensor
     * Extracts location information from the Yaml node and respects the configured severity set by the Rule
     */
    public void newIssue(RuleKey key, OMTElement element, String message) {
        newIssue(key, element.getNode(), message);
    }

    /**
     * Add issue using a direct reference to the Node. Use this if you require non-value nodes to be set.
     * For example, the key in a map is a ScalarNode by itself but is only considered a String-based key
     * by the AbstractMap. Use the findKey() method on the Map to extract the ScalarNode by String-based key reference.
     */
    public void newIssue(RuleKey key, Node node, String message) {
        context.newIssue(new OMTIssue(key, getIssueLocation(node, message)));
    }

    /**
     * Translates the Yaml node to an OMTIssueLocation
     */
    protected OMTIssueLocation getIssueLocation(Node node, String message) {
        // SnakeYAML uses 0-based line counting
        // Sonarqube uses 1-based line counting
        Mark endMark = getEndMark(node);
        return new OMTIssueLocation(
                node.getStartMark().getLine() + 1,
                node.getStartMark().getColumn(),
                endMark.getLine() + 1,
                endMark.getColumn(),
                message
        );
    }

    private Mark getEndMark(Node node) {
        if(node instanceof SequenceNode) {
            List<Node> value = ((SequenceNode) node).getValue();
            return value.get(value.size() - 1).getEndMark();
        } else if(node instanceof MappingNode) {
            List<NodeTuple> value = ((MappingNode) node).getValue();
            return value.get(value.size() - 1).getValueNode().getEndMark();
        }
        return node.getEndMark();
    }

    @Override
    public String getFilename() {
        return context.getFilename();
    }
}
