package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.modelItem.ontology.OntologyClass;
import org.yaml.snakeyaml.nodes.Node;

public class OntologyClassesSequence extends AbstractSequence {
    public OntologyClassesSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new OntologyClass(node);
    }
}
