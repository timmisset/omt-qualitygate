package com.misset.omt.qualitygate.model.maps.rules;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import com.misset.omt.qualitygate.model.maps.Model;
import org.yaml.snakeyaml.nodes.Node;

public class Rules extends AbstractMap {
    public Rules(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new Rule(valueNode);
    }
}
