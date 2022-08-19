package com.misset.omt.qualitygate.model.maps.actions;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.InterpolatedStringElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Action extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    public static final String ICON = "icon";
    public static final String TITLE = "title";
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String PROMOTE_SUB_MENU_ITEM_TO_MAIN_MENU = "promoteSubMenuItemToMainMenu";
    public static final String PRECONDITION = "precondition";
    public static final String DISABLED = "disabled";
    public static final String BUSY_DISABLED = "busyDisabled";
    public static final String DYNAMIC_ACTION_QUERY = "dynamicActionQuery";
    private static final String ON_SELECT = "onSelect";

    static {
        properties.put(ID, StringElement::new);
        properties.put(TITLE, InterpolatedStringElement::new);
        properties.put(DESCRIPTION, InterpolatedStringElement::new);
        properties.put(PROMOTE_SUB_MENU_ITEM_TO_MAIN_MENU, BooleanElement::new);
        properties.put(ICON, StringElement::new);
        properties.put(ParamsSequence.PARAMS, ParamsSequence::new);
        properties.put(PRECONDITION, ODTQuery::new);
        properties.put(DISABLED, BooleanElement::new);
        properties.put(BUSY_DISABLED, BooleanElement::new);
        properties.put(DYNAMIC_ACTION_QUERY, ODTQuery::new);
        properties.put(ON_SELECT, ODTScript::new);
    }

    public Action(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

}
