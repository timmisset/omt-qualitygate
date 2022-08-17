package com.misset.omt.qualitygate.model.maps.actions;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import org.yaml.snakeyaml.nodes.Node;

public class Actions extends AbstractMap {
    public static final String MENU = "menu";
    public static final String ACTIONS = "actions";

    public Actions(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new Action(valueNode);
    }

}
