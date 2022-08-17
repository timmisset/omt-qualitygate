package com.misset.omt.qualitygate.model.maps.variables;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import org.yaml.snakeyaml.nodes.Node;

public class Bindings extends AbstractMap {
    public static final String BINDINGS = "bindings";

    public Bindings(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new Binding(valueNode);
    }

}
