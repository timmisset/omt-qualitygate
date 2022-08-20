package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.SequenceNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractSequence extends OMTBaseElement {

    List<OMTElement> elements;

    protected AbstractSequence(Node node) {
        super(node);
    }

    @Override
    public NodeId getExpectedNodeId() {
        return NodeId.sequence;
    }

    @Override
    protected final void parseNode(Node node) {
        elements = new ArrayList<>();
        if(node instanceof SequenceNode) {
            ((SequenceNode) node)
                    .getValue()
                    .stream()
                    .map(this::parseSequenceItemNode)
                    .forEach(elements::add);
        }
    }

    protected abstract OMTElement parseSequenceItemNode(Node node);

    @Override
    public Collection<OMTElement> getChildren() {
        return elements.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
