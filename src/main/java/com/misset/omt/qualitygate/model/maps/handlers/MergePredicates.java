package com.misset.omt.qualitygate.model.maps.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.FixedValuesStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

public class MergePredicates extends AbstractHandler {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("subjects", ODTQuery::new);
        properties.put("anyPredicate", BooleanElement::new);
        properties.put("predicates", ODTQuery::new);
        properties.put("when", ODTQuery::new);
        properties.put("from", node -> new FixedValuesStringElement(node, List.of("both", "parent")));
        properties.put("use", node -> new FixedValuesStringElement(node, List.of("current", "parent")));
        properties.put("type", node -> new FixedValuesStringElement(node, List.of("create", "delete", "update")));
    }

    public MergePredicates(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
