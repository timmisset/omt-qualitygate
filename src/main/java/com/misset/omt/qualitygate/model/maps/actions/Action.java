package com.misset.omt.qualitygate.model.maps.actions;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.InterpolatedStringElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class Action extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("id", StringElement::new);
        properties.put("title", InterpolatedStringElement::new);
        properties.put("description", InterpolatedStringElement::new);
        properties.put("promoteSubMenuItemToMainMenu", BooleanElement::new);
        properties.put("icon", StringElement::new);
        properties.put("params", ParamsSequence::new);
        properties.put("precondition", ODTQuery::new);
        properties.put("disabled", BooleanElement::new);
        properties.put("busyDisabled", BooleanElement::new);
        properties.put("dynamicActionQuery", ODTQuery::new);
        properties.put("onSelect", ODTScript::new);
    }

    public Action(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

}
