package com.misset.omt.qualitygate.model.maps.variables;

import com.misset.omt.qualitygate.checks.VariableNameMustStartWithSymbol;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictShorthandedMap;
import com.misset.omt.qualitygate.model.scalars.BooleanElement;
import com.misset.omt.qualitygate.model.scalars.VariableNameStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Variable extends AbstractStrictShorthandedMap implements DeclaredVariable {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final HashMap<String, String> shorthandedValues = new HashMap<>();
    private static final Pattern pattern = Pattern.compile("^\\s*(?<name>\\w+)\\s*(?:=\\s*(?<value>.+))?$");
    private static final String NAME = "name";
    private static final String READONLY = "readonly";
    private static final String VALUE = "value";
    private static final String ON_CHANGE = "onChange";

    static {
        properties.put(NAME, VariableNameStringElement::new);
        properties.put(READONLY, BooleanElement::new);
        properties.put(VALUE, ODTQuery::new);
        properties.put(ON_CHANGE, ODTScript::new);

        shorthandedValues.put(NAME, null);
        shorthandedValues.put(VALUE, null);
    }

    public Variable(Node node) {
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
                shorthandedValues.get(NAME) :
                get(NAME, VariableNameStringElement.class).getName();
    }

    @Override
    public void validateTree(SensorContext context, InputFile inputFile) {
        super.validateTree(context, inputFile);
        if(isShorthanded() && VariableNameMustStartWithSymbol.isIssue(context, getName())) {
            newIssue(context, VariableNameMustStartWithSymbol.KEY, inputFile);
        }
    }
}
