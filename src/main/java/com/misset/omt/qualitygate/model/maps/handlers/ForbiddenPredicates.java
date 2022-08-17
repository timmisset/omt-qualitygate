package com.misset.omt.qualitygate.model.maps.handlers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.FixedValuesStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

public class ForbiddenPredicates extends AbstractHandler {

    public static final String FORBIDDEN_PREDICATES = "!ForbiddenPredicates";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final List<String> REQUIRED = List.of(CONTEXT, PREDICATES);

    static {
        properties.put(CONTEXT, node -> new FixedValuesStringElement(node, List.of(CURRENT, PARENT, BOTH)));
        properties.put(PREDICATES, ODTQuery::new);
    }

    public ForbiddenPredicates(Node node) {
        super(node);
    }

    @Override
    protected Collection<String> getRequiredKeys() {
        return REQUIRED;
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
