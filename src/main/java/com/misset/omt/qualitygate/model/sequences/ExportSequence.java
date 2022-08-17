package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.ExportMemberStringElement;
import org.yaml.snakeyaml.nodes.Node;

public class ExportSequence extends AbstractSequence {
    public static final String EXPORT = "export";

    public ExportSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new ExportMemberStringElement(node);
    }
}
