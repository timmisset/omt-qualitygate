package com.misset.omt.qualitygate.model.maps.modelitem.ontology;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.scalars.OntologyPrefixStringElement;
import com.misset.omt.qualitygate.model.sequences.OntologyClassesSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

public class Ontology extends ModelItem {

    public static final String ONTOLOGY = "!Ontology";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    private static final Set<String> REQUIRED = Collections.singleton(OntologyPrefixStringElement.PREFIX);

    static {
        properties.put(OntologyPrefixStringElement.PREFIX, OntologyPrefixStringElement::new);
        properties.put(OntologyClassesSequence.CLASSES, OntologyClassesSequence::new);
    }

    @Override
    protected Collection<String> getRequiredKeys() {
        return REQUIRED;
    }

    public Ontology(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
