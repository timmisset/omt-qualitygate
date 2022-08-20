package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.analyzer.context.OMTSensorContext;
import com.misset.omt.qualitygate.analyzer.issue.OMTIssue;
import com.misset.omt.qualitygate.analyzer.issue.OMTIssueLocation;
import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.validators.Validator;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.model.parser.OMTParser;
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
    private static final OMTParser parser = new OMTParser();
    private final Class<T> elementClass;

    protected OMTSensorContext context;

    private T currentElement;

    protected AbstractElementVisitor(Class<T> elementClass) {
        this.elementClass = elementClass;
    }


    public final void visitElements(OMTSensorContext context) {
        this.context = context;
        OMTFileType fileType = parser.getFileType(context.getFilename());
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

    protected boolean isActive(String key) {
        return context.getActiveRules().contains(key);
    }

    protected void validateIfActive(String key, Validator<T> validator) {
        if (isActive(key)) {
            validator.validate(currentElement);
        }
    }

    public String getPropertyValue(String key, String propertyKey) {
        return Rules.getRule(key).getValue(propertyKey);
    }

    @Override
    public void newIssue(String key, OMTElement element, String... messageArguments) {
        newIssue(key, element.getNode(), messageArguments);
    }

    @Override
    public void newIssue(String key, Node node, String... messageArguments) {
        String message = Rules.getRule(key).getPrimaryMessage(messageArguments);
        context.newIssue(new OMTIssue(key, getIssueLocation(node, message)));
    }

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
