package com.misset.omt.qualitygate.model.scalars;

import org.yaml.snakeyaml.nodes.Node;

public class VariableNameStringElement extends StringElement {
    public VariableNameStringElement(Node node) {
        super(node);
    }

    public String getName() {
        return getNodeAsScalar().getValue();
    }
}
