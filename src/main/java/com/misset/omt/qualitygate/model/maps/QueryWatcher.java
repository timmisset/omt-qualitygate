package com.misset.omt.qualitygate.model.maps;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import org.yaml.snakeyaml.nodes.Node;

public class QueryWatcher extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final String QUERY = "query";
    private static final String ON_CHANGE = "onChange";
    private static final List<String> REQUIRED = List.of(QUERY, ON_CHANGE);

    static {
        properties.put(QUERY, ODTQuery::new);
        properties.put(ON_CHANGE, ODTScript::new);
    }

    public QueryWatcher(Node node) {
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
