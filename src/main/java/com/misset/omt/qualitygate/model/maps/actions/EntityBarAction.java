package com.misset.omt.qualitygate.model.maps.actions;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import org.yaml.snakeyaml.nodes.Node;

public class EntityBarAction extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("icon", StringElement::new);
    }

    public EntityBarAction(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
