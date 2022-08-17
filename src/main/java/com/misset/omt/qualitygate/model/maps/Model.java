package com.misset.omt.qualitygate.model.maps;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.modelitem.Activity;
import com.misset.omt.qualitygate.model.maps.modelitem.Component;
import com.misset.omt.qualitygate.model.maps.modelitem.GraphShapeHandlers;
import com.misset.omt.qualitygate.model.maps.modelitem.Loadable;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.maps.modelitem.Procedure;
import com.misset.omt.qualitygate.model.maps.modelitem.StandaloneQuery;
import com.misset.omt.qualitygate.model.maps.modelitem.ontology.Ontology;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public class Model extends AbstractMap {

    private static final HashMap<String, Function<Node, ModelItem>> modelItemProvider = new HashMap<>();

    static {
        modelItemProvider.put(Activity.ACTIVITY, Activity::new);
        modelItemProvider.put(Component.COMPONENT, Component::new);
        modelItemProvider.put(GraphShapeHandlers.GRAPH_SHAPE_HANDLERS, GraphShapeHandlers::new);
        modelItemProvider.put(Loadable.LOADABLE, Loadable::new);
        modelItemProvider.put(Ontology.ONTOLOGY, Ontology::new);
        modelItemProvider.put(Procedure.PROCEDURE, Procedure::new);
        modelItemProvider.put(StandaloneQuery.STANDALONE_QUERY, StandaloneQuery::new);
    }

    public Model(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return Optional.ofNullable(valueNode.getTag())
                .map(Tag::getValue)
                .map(s -> modelItemProvider.getOrDefault(s, node -> null).apply(valueNode))
                .orElse(null);
    }
}
