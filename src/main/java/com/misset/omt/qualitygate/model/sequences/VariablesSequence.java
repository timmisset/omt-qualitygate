package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.variables.Variable;
import org.yaml.snakeyaml.nodes.Node;

public class VariablesSequence extends AbstractSequence {
    public VariablesSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new Variable(node);
    }
}
