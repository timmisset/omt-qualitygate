package com.misset.omt.qualitygate.model.maps.handlers;

import com.misset.omt.qualitygate.model.maps.AbstractMap;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import org.yaml.snakeyaml.nodes.Node;

public abstract class AbstractHandler extends AbstractStrictMap {

    protected AbstractHandler(Node node) {
        super(node);
    }
}
