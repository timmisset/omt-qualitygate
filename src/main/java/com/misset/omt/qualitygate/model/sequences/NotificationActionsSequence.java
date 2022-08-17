package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.actions.NotificationAction;
import org.yaml.snakeyaml.nodes.Node;

public class NotificationActionsSequence extends AbstractSequence {
    public NotificationActionsSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new NotificationAction(node);
    }
}
