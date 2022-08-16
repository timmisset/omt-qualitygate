package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.modelitem.*;
import com.misset.omt.qualitygate.model.maps.modelitem.ontology.Ontology;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

public class Model extends AbstractMap {

    private static final HashMap<String, Function<Node, ModelItem>> modelItemProvider = new HashMap<>();

    static {
        modelItemProvider.put("!Activity", Activity::new);
        modelItemProvider.put("!Component", Component::new);
        modelItemProvider.put("!GraphShapeHandler", GraphShapeHandlers::new);
        modelItemProvider.put("!Loadable", Loadable::new);
        modelItemProvider.put("!Ontology", Ontology::new);
        modelItemProvider.put("!Procedure", Procedure::new);
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
