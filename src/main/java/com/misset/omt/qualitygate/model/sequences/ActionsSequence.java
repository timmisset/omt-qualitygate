package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.actions.Action;
import org.yaml.snakeyaml.nodes.Node;

public class ActionsSequence extends AbstractSequence {
    public ActionsSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new Action(node);
    }
}
