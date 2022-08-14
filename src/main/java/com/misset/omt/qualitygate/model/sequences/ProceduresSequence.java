package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.scalars.ProcedureMemberStringElement;
import org.yaml.snakeyaml.nodes.Node;

public class ProceduresSequence extends AbstractSequence {
    public ProceduresSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new ProcedureMemberStringElement(node);
    }
}
