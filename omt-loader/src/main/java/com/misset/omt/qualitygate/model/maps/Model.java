package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.maps.modelitem.*;
import com.misset.omt.qualitygate.model.maps.modelitem.ontology.Ontology;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Model extends AbstractTaggedValuesMap<ModelItem> {

    private static final HashMap<String, Function<Node, ModelItem>> TAGGED_ELEMENTS = new HashMap<>();
    static {
        TAGGED_ELEMENTS.put(Activity.ACTIVITY, Activity::new);
        TAGGED_ELEMENTS.put(Component.COMPONENT, Component::new);
        TAGGED_ELEMENTS.put(GraphShapeHandlers.GRAPH_SHAPE_HANDLERS, GraphShapeHandlers::new);
        TAGGED_ELEMENTS.put(Loadable.LOADABLE, Loadable::new);
        TAGGED_ELEMENTS.put(Ontology.ONTOLOGY, Ontology::new);
        TAGGED_ELEMENTS.put(Procedure.PROCEDURE, Procedure::new);
        TAGGED_ELEMENTS.put(StandaloneQuery.STANDALONE_QUERY, StandaloneQuery::new);
    }
    public Model(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, ModelItem>> getTaggedElements() {
        return TAGGED_ELEMENTS;
    }
}
