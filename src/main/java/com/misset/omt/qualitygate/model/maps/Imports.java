package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.sequences.ImportSequence;
import org.yaml.snakeyaml.nodes.Node;

public class Imports extends AbstractMap {

    public Imports(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new ImportSequence(valueNode);
    }


}
