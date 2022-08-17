package com.misset.omt.qualitygate.model.maps;

import java.util.Collection;
import java.util.Collections;
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

    /**
     * Override to provide a set of keys that are currently missing from the map
     */
    protected Collection<String> getRequiredKeys() {
        return Collections.emptyList();
    }

    protected abstract HashMap<String, Function<Node, OMTElement>> getProperties();
}
