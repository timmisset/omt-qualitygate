package com.misset.omt.qualitygate.model.maps.actions;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.InterpolatedStringElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class DossierAction extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final List<String> REQUIRED = List.of(Action.TITLE, Action.ICON, ParamsSequence.PARAMS);

    static {
        properties.put(Action.TITLE, InterpolatedStringElement::new);
        properties.put(Action.ICON, StringElement::new);
        properties.put(ParamsSequence.PARAMS, ParamsSequence::new);
    }

    public DossierAction(Node node) {
        super(node);
    }

    @Override
    protected Collection<String> getRequiredKeys() {
        return REQUIRED;
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
