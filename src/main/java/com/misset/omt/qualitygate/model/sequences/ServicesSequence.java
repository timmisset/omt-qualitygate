package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.Service;
import org.yaml.snakeyaml.nodes.Node;

public class ServicesSequence extends AbstractSequence {
    public ServicesSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new Service(node);
    }
}
