package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.variables.Param;
import org.yaml.snakeyaml.nodes.Node;

public class ParamsSequence extends AbstractSequence {
    public ParamsSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new Param(node);
    }
}
