package com.misset.omt.qualitygate.model.maps.modelItem;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.GraphSelection;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class StandaloneQuery extends ModelItem {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("base", VariableNameStringElement::new);
        properties.put("params", ParamsSequence::new);
        properties.put("graphs", GraphSelection::new);
        properties.put("prefixes", Prefixes::new);
        properties.put("query", ODTQuery::new);
    }

    public StandaloneQuery(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
