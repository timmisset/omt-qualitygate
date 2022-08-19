package com.misset.omt.qualitygate.model.maps.payload;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class PayloadItem extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    public static final String VALUE = "value";

    public static final String LIST = "list";

    static {
        properties.put(VALUE, ODTQuery::new);
        properties.put(ODTQuery.QUERY, ODTQuery::new);
        properties.put(LIST, BooleanElement::new);
        properties.put(ODTScript.ON_CHANGE, ODTScript::new);
    }

    public PayloadItem(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
