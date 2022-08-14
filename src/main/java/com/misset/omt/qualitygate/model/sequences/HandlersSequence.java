package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.maps.handlers.*;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.HashMap;
import java.util.function.Function;

public class HandlersSequence extends AbstractSequence {

    private static final HashMap<String, Function<Node, AbstractHandler>> handlers = new HashMap<>();

    static {
        handlers.put("!MergePredicates", MergePredicates::new);
        handlers.put("!MergeLists", MergeLists::new);
        handlers.put("!MergeValidation", MergeValidation::new);
        handlers.put("!ForbiddenPredicates", ForbiddenPredicates::new);
    }

    public HandlersSequence(Node node) {
        super(node);
    }

    @Override
    protected OMTBaseElement createInstance(Node node) {
        Tag tag = node.getTag();
        if(tag != null) {
            return handlers.getOrDefault(tag.getValue(), unknownKey -> null).apply(node);
        }
        return null;
    }
}
