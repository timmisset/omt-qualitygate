package com.misset.omt.qualitygate.model.maps.variables;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictShorthandedMap;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import org.yaml.snakeyaml.nodes.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Param extends AbstractStrictShorthandedMap implements DeclaredVariable {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final Pattern pattern = Pattern.compile("^(?<name>\\$?\\w+)\\s*(\\((?<type>[^)]+)\\))?$");
    public static final String NAME = "name";
    public static final String TYPE = "type";
    private static final List<String> REQUIRED = List.of(NAME);
    private static final Collection<String> SHORTHANDED_VALUE_KEYS = Set.of(NAME, TYPE);

    static {
        properties.put(NAME, VariableNameStringElement::new);
        properties.put(TYPE, StringElement::new);
    }

    public Param(Node node) {
        super(node);
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public Collection<String> getShorthandedValueKeys() {
        return SHORTHANDED_VALUE_KEYS;
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
        return isShorthanded() ?
                shorthandedValues.get(NAME) :
                get(NAME, VariableNameStringElement.class).getName();
    }
}
