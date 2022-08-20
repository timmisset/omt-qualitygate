package com.misset.omt.qualitygate.model.maps.handlers;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.FixedValuesStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class MergeLists extends AbstractHandler {

    public static final String MERGE_LISTS = "!MergeLists";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final List<String> REQUIRED = List.of(SUBJECTS, FROM);

    static {
        properties.put(SUBJECTS, ODTQuery::new);
        properties.put(ANY_PREDICATE, BooleanElement::new);
        properties.put(PREDICATES, ODTQuery::new);
        properties.put(WHEN, ODTQuery::new);
        properties.put(FROM, node -> new FixedValuesStringElement(node, List.of(BOTH, PARENT)));
    }

    public MergeLists(Node node) {
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