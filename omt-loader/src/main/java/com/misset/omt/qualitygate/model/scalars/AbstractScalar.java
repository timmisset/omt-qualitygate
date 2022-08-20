package com.misset.omt.qualitygate.model.scalars;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractScalar extends OMTBaseElement {

    protected AbstractScalar(Node node) {
        super(node);
    }

    @Override
    public NodeId getExpectedNodeId() {
        return NodeId.scalar;
    }

    @Override
    protected void parseNode(Node node) {

    }

    protected ScalarNode getNodeAsScalar() {
        return getAsNode(ScalarNode.class);
    }
    @Override
    public Collection<OMTElement> getChildren() {
        return new ArrayList<>();
    }

    public String getValueOrEmpty() {
        return Optional.ofNullable(getNodeAsScalar())
                .map(ScalarNode::getValue)
                .orElse("");
    }
}
