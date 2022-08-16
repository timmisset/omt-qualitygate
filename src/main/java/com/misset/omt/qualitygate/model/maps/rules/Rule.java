package com.misset.omt.qualitygate.model.maps.rules;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

public class Rule extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("query", ODTQuery::new);
        properties.put("strict", ODTQuery::new);
    }

    public Rule(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
