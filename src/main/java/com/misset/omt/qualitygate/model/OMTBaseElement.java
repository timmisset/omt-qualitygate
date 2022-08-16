package com.misset.omt.qualitygate.model;

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

    protected OMTBaseElement(Node node) {
        this.node = node;
        parseNode(node);
    }

    public Node getNode() {
        return node;
    }

    protected <T extends Node> T getAsNode(Class<T> asNode) {
        if(asNode.isAssignableFrom(node.getClass())) {
            return asNode.cast(node);
        }
        return null;
    }

    public abstract NodeId getExpectedNodeId();

    protected abstract void parseNode(Node node);
}
