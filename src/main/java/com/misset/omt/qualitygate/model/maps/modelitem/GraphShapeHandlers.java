package com.misset.omt.qualitygate.model.maps.modelitem;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.sequences.HandlersSequence;
import org.yaml.snakeyaml.nodes.Node;

public class GraphShapeHandlers extends ModelItem {

    public static final String GRAPH_SHAPE_HANDLERS = "!GraphShapeHandlers";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    private static final String SHAPE = "shape";
    private static final Set<String> REQUIRED = Collections.singleton(SHAPE);
    private static final String ID = "id";

    static {
        properties.put(ID, StringElement::new);
        properties.put(SHAPE, ODTQuery::new);
        properties.put(HandlersSequence.HANDLERS, HandlersSequence::new);
    }

    @Override
    protected Collection<String> getRequiredKeys() {
        return REQUIRED;
    }

    public GraphShapeHandlers(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
