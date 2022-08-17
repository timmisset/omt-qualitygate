package com.misset.omt.qualitygate.model.maps.modelitem;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import org.yaml.snakeyaml.nodes.Node;

public class Loadable extends ModelItem {

    public static final String LOADABLE = "!Loadable";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final String PATH = "path";
    private static final String SCHEMA = "schema";
    private static final List<String> REQUIRED = List.of(PATH, SCHEMA);
    private static final String ID = "id";

    static {
        properties.put(ID, StringElement::new);
        properties.put(PATH, StringElement::new);
        properties.put(SCHEMA, StringElement::new);
    }

    public Loadable(Node node) {
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
