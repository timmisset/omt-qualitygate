package com.misset.omt.qualitygate.model.maps.handlers;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.FixedValuesStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class ForbiddenPredicates extends AbstractHandler {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("context", node -> new FixedValuesStringElement(node, List.of("current", "parent", "both")));
        properties.put("predicates", ODTQuery::new);
    }

    public ForbiddenPredicates(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
