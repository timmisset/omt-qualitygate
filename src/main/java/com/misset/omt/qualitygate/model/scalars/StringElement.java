package com.misset.omt.qualitygate.model.scalars;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

public class StringElement extends OMTBaseElement {
    public StringElement(Node node) {
        super(node);
    }

    @Override
    protected NodeId getExpectedNodeId() {
        return NodeId.scalar;
    }

    @Override
    protected void parseNode(Node node) {

    }
}
