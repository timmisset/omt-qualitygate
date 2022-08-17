package com.misset.omt.qualitygate.model.scalars.injected;

import com.misset.omt.qualitygate.model.scalars.StringElement;
import org.yaml.snakeyaml.nodes.Node;

/**
 * Placeholder that can receive an injected fragment that is considered a query
 */
public class ODTQuery extends StringElement {

    public static final String QUERY = "query";

    public ODTQuery(Node node) {
        super(node);
    }
}
