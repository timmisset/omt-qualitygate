package com.misset.omt.qualitygate.model.maps.modelItem;

import com.misset.omt.qualitygate.model.maps.AbstractMap;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import org.yaml.snakeyaml.nodes.Node;

public abstract class ModelItem extends AbstractStrictMap {

    protected ModelItem(Node node) {
        super(node);
    }
}
