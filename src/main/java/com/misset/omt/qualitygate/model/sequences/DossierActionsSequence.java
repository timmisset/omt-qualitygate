package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.actions.DossierAction;
import org.yaml.snakeyaml.nodes.Node;

public class DossierActionsSequence extends AbstractSequence {
    public DossierActionsSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new DossierAction(node);
    }
}
