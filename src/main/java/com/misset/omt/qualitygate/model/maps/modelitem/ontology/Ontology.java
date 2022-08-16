package com.misset.omt.qualitygate.model.maps.modelitem.ontology;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.scalars.OntologyPrefixStringElement;
import com.misset.omt.qualitygate.model.sequences.OntologyClassesSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Ontology extends ModelItem {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("prefix", OntologyPrefixStringElement::new);
        properties.put("classes", OntologyClassesSequence::new);
    }

    public Ontology(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
