package com.misset.omt.qualitygate.model.maps;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

public abstract class AbstractStrictShorthandedMap extends AbstractStrictMap {

    private final boolean isShorthanded;
    protected HashMap<String, String> shorthandedValues;
    protected AbstractStrictShorthandedMap(Node node) {
        super(node);
        if(node instanceof ScalarNode) {
            // shorthanded, this means the map based parseNode command is not called
            // we need to parse the shorthand notation and from the capturing groups
            // extract the relevant information
            shorthandedValues = new HashMap<>();
            parseRegex();
            isShorthanded = true;
        } else {
            isShorthanded = false;
        }
    }

    private void parseRegex() {
        String value = ((ScalarNode) getNode()).getValue();
        Matcher matcher = getPattern().matcher(value);
        if(matcher.find()) {
            getShorthandedValueKeys().forEach(s -> shorthandedValues.put(s, matcher.group(s)));
        }
    }

    protected abstract Pattern getPattern();
    protected abstract List<String> getShorthandedValueKeys();

    protected boolean isShorthanded() {
        return isShorthanded;
    }
}
