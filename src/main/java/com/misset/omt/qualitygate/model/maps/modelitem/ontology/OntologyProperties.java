package com.misset.omt.qualitygate.model.maps.modelitem.ontology;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractMap;
import org.yaml.snakeyaml.nodes.Node;

public class OntologyProperties extends AbstractMap {
    static final String PROPERTIES = "properties";

    public OntologyProperties(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return new OntologyProperty(valueNode);
    }

}
