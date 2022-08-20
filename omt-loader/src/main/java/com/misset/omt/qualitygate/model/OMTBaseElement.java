package com.misset.omt.qualitygate.model;

import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

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
