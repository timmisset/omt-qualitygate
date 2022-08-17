package com.misset.omt.qualitygate.model.maps.variables;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictShorthandedMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import org.yaml.snakeyaml.nodes.Node;

public class Binding extends AbstractStrictShorthandedMap implements DeclaredVariable {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final Pattern PATTERN = Pattern.compile("^\\s*(?<bindTo>\\$?\\w+)$");
    private static final String BIND_TO = "bindTo";
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String ON_CHANGE = "onChange";
    private static final Set<String> REQUIRED = Collections.singleton(BIND_TO);

    static {
        properties.put(BIND_TO, VariableNameStringElement::new);
        properties.put(INPUT, BooleanElement::new);
        properties.put(OUTPUT, BooleanElement::new);
        properties.put(ON_CHANGE, ODTScript::new);
    }

    public Binding(Node node) {
        super(node);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public Collection<String> getShorthandedValueKeys() {
        return Set.of(BIND_TO);
    }

    @Override
    protected Collection<String> getRequiredKeysIfNotShorthanded() {
        return REQUIRED;
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

    @Override
    public String getName() {
        return get(BIND_TO, VariableNameStringElement.class).getName();
    }
}
