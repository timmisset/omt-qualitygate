package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.ProcedureMemberStringElement;
import org.yaml.snakeyaml.nodes.Node;

public class ProceduresSequence extends AbstractSequence {
    public static final String PROCEDURES = "procedures";

    public ProceduresSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new ProcedureMemberStringElement(node);
    }
}
