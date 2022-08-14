package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.scalars.ExportMemberStringElement;
import org.yaml.snakeyaml.nodes.Node;

public class ExportSequence extends AbstractSequence {
    public ExportSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new ExportMemberStringElement(node);
    }
}
