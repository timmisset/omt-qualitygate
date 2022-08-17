package com.misset.omt.qualitygate.model.maps.actions;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.sequences.ActionsSequence;
import com.misset.omt.qualitygate.model.sequences.DossierActionsSequence;
import com.misset.omt.qualitygate.model.sequences.NotificationActionsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class GlobalActions extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final String NOTIFICATIONS = "notifications";
    private static final String BOTTOM_NAVIGATION = "bottomNavigation";
    private static final String DASHBOARD = "dashboard";
    private static final String DASHBOARD_ACTIONS = "dashboardActions";
    private static final String DOSSIER = "dossier";
    private static final String FIXED = "fixed";
    private static final String BESTANDSTATUS = "bestandstatus";
    private static final String ENTITYBAR = "entitybar";

    static {
        properties.put(NOTIFICATIONS, NotificationActionsSequence::new);
        properties.put(BOTTOM_NAVIGATION, ActionsSequence::new);
        properties.put(DASHBOARD, ActionsSequence::new);
        properties.put(DASHBOARD_ACTIONS, ActionsSequence::new);
        properties.put(DOSSIER, DossierActionsSequence::new);
        properties.put(FIXED, ActionsSequence::new);
        properties.put(BESTANDSTATUS, ActionsSequence::new);
        properties.put(ENTITYBAR, EntitybarAction::new);
    }

    public GlobalActions(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
