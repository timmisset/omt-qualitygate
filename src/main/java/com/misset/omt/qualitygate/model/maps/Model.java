package com.misset.omt.qualitygate.model.maps;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.maps.modelitem.Activity;
import com.misset.omt.qualitygate.model.maps.modelitem.Component;
import com.misset.omt.qualitygate.model.maps.modelitem.GraphShapeHandlers;
import com.misset.omt.qualitygate.model.maps.modelitem.Loadable;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.maps.modelitem.Procedure;
import com.misset.omt.qualitygate.model.maps.modelitem.StandaloneQuery;
import com.misset.omt.qualitygate.model.maps.modelitem.ontology.Ontology;
import org.yaml.snakeyaml.nodes.Node;

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
