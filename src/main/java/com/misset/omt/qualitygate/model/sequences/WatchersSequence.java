package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.QueryWatcher;
import org.yaml.snakeyaml.nodes.Node;

public class WatchersSequence extends AbstractSequence {
    public static final String WATCHERS = "watchers";

    public WatchersSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        return new QueryWatcher(node);
    }
}
