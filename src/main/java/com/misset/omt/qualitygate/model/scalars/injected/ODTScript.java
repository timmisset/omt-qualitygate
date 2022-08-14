package com.misset.omt.qualitygate.model.scalars.injected;

import com.misset.omt.qualitygate.model.scalars.StringElement;
import org.yaml.snakeyaml.nodes.Node;

/**
 * Placeholder that can receive an injected fragment that is considered a script
 */
public class ODTScript extends StringElement {

    public ODTScript(Node node) {
        super(node);
    }
}
