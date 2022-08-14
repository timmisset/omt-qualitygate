package com.misset.omt.qualitygate.model.maps.payload;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import com.misset.omt.qualitygate.model.maps.Model;
import org.yaml.snakeyaml.nodes.Node;

public class Payload extends AbstractMap {
    public Payload(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new PayloadItem(valueNode);
    }

}
