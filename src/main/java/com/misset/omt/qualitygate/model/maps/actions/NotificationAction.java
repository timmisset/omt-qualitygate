package com.misset.omt.qualitygate.model.maps.actions;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.InterpolatedStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class NotificationAction extends AbstractStrictMap {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    private static final String ON_MARK_AS_READ = "onMarkAsRead";

    private static final String ON_DELETE = "onDelete";

    static {
        properties.put(Action.TITLE, InterpolatedStringElement::new);
        properties.put(Action.DESCRIPTION, InterpolatedStringElement::new);
        properties.put(ON_MARK_AS_READ, ODTScript::new);
        properties.put(ON_DELETE, ODTScript::new);
        properties.put(ParamsSequence.PARAMS, ParamsSequence::new);
        properties.put(Action.PROMOTE_SUB_MENU_ITEM_TO_MAIN_MENU, BooleanElement::new);
    }

    public NotificationAction(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
