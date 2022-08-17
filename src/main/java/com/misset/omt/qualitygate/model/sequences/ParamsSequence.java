package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.variables.Param;
import org.yaml.snakeyaml.nodes.Node;

public class ParamsSequence extends AbstractSequence {
    public static final String PARAMS = "params";

    public ParamsSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new Param(node);
    }
}
