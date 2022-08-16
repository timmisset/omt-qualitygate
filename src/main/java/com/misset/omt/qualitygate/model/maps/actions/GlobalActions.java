package com.misset.omt.qualitygate.model.maps.actions;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.sequences.ActionsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class GlobalActions extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("notifications", ActionsSequence::new);
        properties.put("bottomNavigation", ActionsSequence::new);
        properties.put("dashboard", ActionsSequence::new);
        properties.put("dashboardActions", ActionsSequence::new);
        properties.put("dossier", ActionsSequence::new);
        properties.put("fixed", ActionsSequence::new);
        properties.put("bestandstatus", ActionsSequence::new);
        properties.put("entitybar", EntityBarAction::new);
    }

    public GlobalActions(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
