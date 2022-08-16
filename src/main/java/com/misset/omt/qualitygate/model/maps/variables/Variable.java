package com.misset.omt.qualitygate.model.maps.variables;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictShorthandedMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import org.yaml.snakeyaml.nodes.Node;

public class Variable extends AbstractStrictShorthandedMap implements DeclaredVariable {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final Pattern pattern = Pattern.compile("^\\s*(?<name>\\$?\\w+)\\s*(?:=\\s*(?<value>.+))?$");
    private static final String NAME = "name";
    private static final String READONLY = "readonly";
    private static final String VALUE = "value";
    private static final String ON_CHANGE = "onChange";
    private static final List<String> SHORTHANDED_VALUE_KEYS = List.of(NAME, VALUE);

    static {
        properties.put(NAME, VariableNameStringElement::new);
        properties.put(READONLY, BooleanElement::new);
        properties.put(VALUE, ODTQuery::new);
        properties.put(ON_CHANGE, ODTScript::new);
    }

    public Variable(Node node) {
        super(node);
    }

    @Override
    protected Pattern getPattern() {
        return pattern;
    }

    @Override
    protected List<String> getShorthandedValueKeys() {
        return SHORTHANDED_VALUE_KEYS;
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

    @Override
    public String getName() {
        return isShorthanded() ? shorthandedValues.get(NAME) : get(NAME, VariableNameStringElement.class).getName();
    }

}
