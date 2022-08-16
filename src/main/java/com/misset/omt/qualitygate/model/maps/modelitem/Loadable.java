package com.misset.omt.qualitygate.model.maps.modelitem;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import org.yaml.snakeyaml.nodes.Node;

public class Loadable extends ModelItem {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("id", StringElement::new);
        properties.put("path", StringElement::new);
        properties.put("schema", StringElement::new);
    }

    public Loadable(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
