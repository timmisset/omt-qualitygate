package com.misset.omt.qualitygate.model.maps.modelitem.ontology;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import org.yaml.snakeyaml.nodes.Node;

public class OntologyClass extends ModelItem {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    private static final String ID = "id";

    static {
        properties.put(ID, StringElement::new);
        properties.put(OntologyProperties.PROPERTIES, OntologyProperties::new);
    }

    public OntologyClass(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
