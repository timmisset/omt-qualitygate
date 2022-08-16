package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.scalars.ImportMemberStringElement;
import org.yaml.snakeyaml.nodes.Node;

public class ImportSequence extends AbstractSequence {
    public ImportSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new ImportMemberStringElement(node);
    }
}
