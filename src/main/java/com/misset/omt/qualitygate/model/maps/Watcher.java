package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Watcher extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("query", ODTQuery::new);
        properties.put("onChange", ODTScript::new);
    }

    public Model model;

    public Watcher(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

}
