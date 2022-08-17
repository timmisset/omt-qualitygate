package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.actions.Action;
import org.yaml.snakeyaml.nodes.Node;

public class ActionsSequence extends AbstractSequence {
    public ActionsSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new Action(node);
    }
}
