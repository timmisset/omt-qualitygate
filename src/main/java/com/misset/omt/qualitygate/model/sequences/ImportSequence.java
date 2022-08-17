package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.ImportMemberStringElement;
import org.yaml.snakeyaml.nodes.Node;

public class ImportSequence extends AbstractSequence {
    public ImportSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new ImportMemberStringElement(node);
    }
}
