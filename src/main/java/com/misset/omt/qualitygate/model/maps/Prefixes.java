package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.scalars.PrefixNamespaceIriElement;
import org.yaml.snakeyaml.nodes.Node;

public class Prefixes extends AbstractMap {
    public Prefixes(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new PrefixNamespaceIriElement(valueNode);
    }

}
