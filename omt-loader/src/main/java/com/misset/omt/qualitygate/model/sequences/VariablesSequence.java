package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.variables.Variable;
import org.yaml.snakeyaml.nodes.Node;

public class VariablesSequence extends AbstractSequence {
    public static final String VARIABLES = "variables";

    public VariablesSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new Variable(node);
    }
}
