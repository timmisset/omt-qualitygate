package com.misset.omt.qualitygate.model.maps.modelItem.ontology;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import com.misset.omt.qualitygate.model.maps.Model;
import org.yaml.snakeyaml.nodes.Node;

public class OntologyProperties extends AbstractMap {
    public OntologyProperties(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new OntologyProperty(valueNode);
    }

}
