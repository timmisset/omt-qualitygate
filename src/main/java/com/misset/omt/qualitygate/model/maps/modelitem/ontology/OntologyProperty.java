package com.misset.omt.qualitygate.model.maps.modelitem.ontology;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.IntegerElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import org.yaml.snakeyaml.nodes.Node;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

public class OntologyProperty extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    private static final String TYPE = "type";
    private static final Set<String> REQUIRED_KEYS = Collections.singleton(TYPE);

    private static final String REQUIRED = "required";

    private static final String MULTIPLE = "multiple";

    private static final String MAX_CARDINALITY = "maxCardinality";

    static {
        properties.put(TYPE, StringElement::new);
        properties.put(REQUIRED, BooleanElement::new);
        properties.put(MULTIPLE, BooleanElement::new);
        properties.put(MAX_CARDINALITY, IntegerElement::new);
    }

    public OntologyProperty(Node node) {
        super(node);
    }

    @Override
    protected Collection<String> getRequiredKeys() {
        return REQUIRED_KEYS;
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
