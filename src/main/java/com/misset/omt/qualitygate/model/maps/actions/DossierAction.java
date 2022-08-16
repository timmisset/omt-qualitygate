package com.misset.omt.qualitygate.model.maps.actions;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.InterpolatedStringElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class DossierAction extends AbstractStrictMap {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("title", InterpolatedStringElement::new);
        properties.put("icon", StringElement::new);
        properties.put("params", ParamsSequence::new);
    }

    public DossierAction(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
