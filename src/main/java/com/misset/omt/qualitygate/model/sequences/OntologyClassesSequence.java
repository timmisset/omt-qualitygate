package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.modelitem.ontology.OntologyClass;
import org.yaml.snakeyaml.nodes.Node;

public class OntologyClassesSequence extends AbstractSequence {
    public static final String CLASSES = "classes";

    public OntologyClassesSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new OntologyClass(node);
    }
}
