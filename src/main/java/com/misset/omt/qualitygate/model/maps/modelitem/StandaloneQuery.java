package com.misset.omt.qualitygate.model.maps.modelitem;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.GraphSelection;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class StandaloneQuery extends ModelItem {

    public static final String STANDALONE_QUERY = "!StandaloneQuery";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final List<String> REQUIRED = List.of(ODTQuery.QUERY, GraphSelection.GRAPHS);

    private static final String BASE = "base";

    static {
        properties.put(BASE, VariableNameStringElement::new);
        properties.put(ParamsSequence.PARAMS, ParamsSequence::new);
        properties.put(GraphSelection.GRAPHS, GraphSelection::new);
        properties.put(Prefixes.PREFIXES, Prefixes::new);
        properties.put(ODTQuery.QUERY, ODTQuery::new);
    }

    public StandaloneQuery(Node node) {
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
