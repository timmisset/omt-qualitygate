package com.misset.omt.qualitygate.model.maps.files.interfacefile;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import org.yaml.snakeyaml.nodes.Node;

public class DeclareModule extends AbstractMap {
    static final String DECLARE = "declare";

    public DeclareModule(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new DeclareInterface(valueNode);
    }
}
