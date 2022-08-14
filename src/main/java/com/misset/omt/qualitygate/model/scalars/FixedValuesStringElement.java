package com.misset.omt.qualitygate.model.scalars;

import org.yaml.snakeyaml.nodes.Node;

import java.util.List;

public class FixedValuesStringElement extends StringElement {

    private final List<String> acceptedValues;

    public FixedValuesStringElement(Node node, List<String> acceptedValues) {
        super(node);
        this.acceptedValues = acceptedValues;
    }
}
