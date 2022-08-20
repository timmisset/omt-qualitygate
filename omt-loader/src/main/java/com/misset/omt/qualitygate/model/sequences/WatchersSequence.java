package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.QueryWatcher;
import org.yaml.snakeyaml.nodes.Node;

public class WatchersSequence extends AbstractSequence {
    public static final String WATCHERS = "watchers";

    public WatchersSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTElement parseSequenceItemNode(Node node) {
        return new QueryWatcher(node);
    }
}
