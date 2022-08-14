package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTElement;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractStrictShorthandedMap extends AbstractMap {

    private final boolean isShorthanded;
    protected AbstractStrictShorthandedMap(Node node) {
        super(node);
        if(node instanceof ScalarNode) {
            // shorthanded, this means the map based parseNode command is not called
            // we need to parse the shorthand notation and from the capturing groups
            // extract the relevant information
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
            getShorthandedValues().keySet()
                    .forEach(s -> getShorthandedValues().put(s, matcher.group(s)));
        }
    }

    protected abstract Pattern getPattern();
    protected abstract HashMap<String, String> getShorthandedValues();

    @Override
    protected final OMTElement parseNode(String key, Node valueNode) {
        return getProperties().getOrDefault(key, unknownKey -> null).apply(valueNode);
    }

    protected abstract HashMap<String, Function<Node, OMTElement>> getProperties();

    protected boolean isShorthanded() {
        return isShorthanded;
    }
}
