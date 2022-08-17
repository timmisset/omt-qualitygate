package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.Service;
import org.yaml.snakeyaml.nodes.Node;

public class ServicesSequence extends AbstractSequence {
    public static final String SERVICES = "services";

    public ServicesSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new Service(node);
    }
}
