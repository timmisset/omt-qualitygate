package com.misset.omt.qualitygate.model;

import org.sonar.api.batch.fs.InputComponent;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

public abstract class OMTBaseElement implements OMTElement {

    private final Node node;
    private boolean validated = false;

    protected OMTBaseElement(Node node) {
        this.node = node;
        parseNode(node);
    }

    protected Node getNode() {
        return node;
    }

    protected ScalarNode getNodeAsScalar() {
        return getAsNode(ScalarNode.class);
    }
    private <T extends Node> T getAsNode(Class<T> asNode) {
        if(asNode.isAssignableFrom(node.getClass())) {
            return asNode.cast(node);
        }
        return null;
    }

    protected abstract NodeId getExpectedNodeId();

    protected abstract void parseNode(Node node);

    protected void validateTree(SensorContext context, InputFile inputFile) {
//        if(node.getNodeId() != getExpectedNodeId()) {
//            addViolation(UNEXPECTED_NODE, getExpectedNodeId().name(), node.getNodeId().name());
//        }
        validated = true;
    }

    /**
     * Most basic method to add a new issue to the sensor
     * Extracts location information from the Yaml node and respects the configured severity set by the Rule
     */
    protected void newIssue(SensorContext context, RuleKey key, InputFile inputFile) {
        NewIssue newIssue = context.newIssue();
        newIssue.forRule(key);
        NewIssueLocation newIssueLocation = newIssue.newLocation();

        newIssueLocation.on(inputFile);
        newIssueLocation.at(getTextRange(inputFile));
    }

    /**
     * Translates the Yaml node to a TextRange for the Sonarqube Sensor
     */
    protected TextRange getTextRange(InputFile inputFile) {
        return inputFile.newRange(
                node.getStartMark().getLine(),
                node.getStartMark().getColumn(),
                node.getEndMark().getLine(),
                node.getEndMark().getColumn()
        );
    }

    public final void validate(SensorContext context, InputFile inputFile) {
        validated = false;
        validateTree(context, inputFile);
        if(!validated) {
            throw new RuntimeException("Validation branch was interrupted. A class extending OMTElement does not call super.validate");
        }
    }
}
