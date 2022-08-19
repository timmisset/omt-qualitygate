package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class GraphSelection extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    public static final String GRAPHS = "graphs";

    public static final String LIVE = "live";

    public static final String EDIT = "edit";

    static {
        properties.put(LIVE, ODTQuery::new);
        properties.put(EDIT, ODTQuery::new);
    }

    public GraphSelection(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
