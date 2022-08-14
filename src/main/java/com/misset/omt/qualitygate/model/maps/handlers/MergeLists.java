package com.misset.omt.qualitygate.model.maps.handlers;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.FixedValuesStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class MergeLists extends AbstractHandler {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("subjects", ODTQuery::new);
        properties.put("anyPredicate", BooleanElement::new);
        properties.put("predicates", ODTQuery::new);
        properties.put("when", ODTQuery::new);
        properties.put("from", node -> new FixedValuesStringElement(node, List.of("both", "parent")));
    }

    public MergeLists(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
