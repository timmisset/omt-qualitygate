package com.misset.omt.qualitygate.model.maps.variables;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictShorthandedMap;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Param extends AbstractStrictShorthandedMap implements DeclaredVariable {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final HashMap<String, String> shorthandedValues = new HashMap<>();
    private static final Pattern pattern = Pattern.compile("^(?<name>\\$\\w+)\\s*(\\((?<type>[^)]+)\\))?$");

    static {
        properties.put("name", VariableNameStringElement::new);
        properties.put("type", StringElement::new);

        shorthandedValues.put("name", null);
        shorthandedValues.put("type", null);
    }

    public Param(Node node) {
        super(node);
    }

    @Override
    protected Pattern getPattern() {
        return pattern;
    }

    @Override
    protected HashMap<String, String> getShorthandedValues() {
        return shorthandedValues;
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

    @Override
    public String getName() {
        return isShorthanded() ?
                shorthandedValues.get("name") :
                get("name", VariableNameStringElement.class).getName();
    }
}
