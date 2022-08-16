package com.misset.omt.qualitygate.model.maps;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import org.yaml.snakeyaml.nodes.Node;

public abstract class AbstractStrictMap extends AbstractMap {

    protected AbstractStrictMap(Node node) {
        super(node);
    }

    @Override
    protected final OMTElement parseNode(String key, Node valueNode) {
        return getProperties().getOrDefault(key, unknownKey -> null).apply(valueNode);
    }

    protected abstract HashMap<String, Function<Node, OMTElement>> getProperties();
}
