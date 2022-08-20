package com.misset.omt.qualitygate.model.maps.modelitem;

import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import org.yaml.snakeyaml.nodes.Node;

public abstract class ModelItem extends AbstractStrictMap {

    public static final String TITLE = "title";

    protected ModelItem(Node node) {
        super(node);
    }
}
