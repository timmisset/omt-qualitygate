package com.misset.omt.qualitygate.model.maps.actions;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import com.misset.omt.qualitygate.model.maps.Model;
import org.yaml.snakeyaml.nodes.Node;

public class Actions extends AbstractMap {
    public Actions(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new Action(valueNode);
    }

}
