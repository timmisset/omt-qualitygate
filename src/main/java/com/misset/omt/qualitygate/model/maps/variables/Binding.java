package com.misset.omt.qualitygate.model.maps.variables;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Binding extends AbstractStrictMap implements DeclaredVariable {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("bindTo", VariableNameStringElement::new);
        properties.put("input", BooleanElement::new);
        properties.put("output", BooleanElement::new);
        properties.put("onChange", ODTScript::new);
    }

    public Binding(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

    @Override
    public String getName() {
        return get("bindTo", VariableNameStringElement.class).getName();
    }
}
