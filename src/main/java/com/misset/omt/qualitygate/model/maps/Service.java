package com.misset.omt.qualitygate.model.maps;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class Service extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("params", ParamsSequence::new);
        properties.put("graphs", GraphSelection::new);
        properties.put("prefixes", Prefixes::new);
        properties.put("onRequest", ODTScript::new);
    }

    public Service(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
