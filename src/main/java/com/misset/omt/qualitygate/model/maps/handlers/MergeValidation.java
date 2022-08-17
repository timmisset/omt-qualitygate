package com.misset.omt.qualitygate.model.maps.handlers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.FixedValuesStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

public class MergeValidation extends AbstractHandler {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final List<String> REQUIRED = List.of(CONTEXT, QUERY);

    static {
        properties.put(CONTEXT, node -> new FixedValuesStringElement(node, List.of(CURRENT, PARENT, BOTH)));
        properties.put(QUERY, ODTQuery::new);
    }

    public MergeValidation(Node node) {
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
