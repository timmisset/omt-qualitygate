package com.misset.omt.qualitygate.model.maps.modelItem;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.sequences.HandlersSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class GraphShapeHandlers extends ModelItem {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("id", StringElement::new);
        properties.put("shape", ODTQuery::new);
        properties.put("handlers", HandlersSequence::new);
    }

    public GraphShapeHandlers(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
