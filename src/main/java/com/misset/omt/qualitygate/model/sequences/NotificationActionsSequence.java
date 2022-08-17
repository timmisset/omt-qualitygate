package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.actions.NotificationAction;
import org.yaml.snakeyaml.nodes.Node;

public class NotificationActionsSequence extends AbstractSequence {
    public NotificationActionsSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new NotificationAction(node);
    }
}
