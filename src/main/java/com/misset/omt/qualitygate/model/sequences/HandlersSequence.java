package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.maps.handlers.*;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class HandlersSequence extends AbstractTaggedValuesSequence<AbstractHandler> {

    private static final HashMap<String, Function<Node, AbstractHandler>> TAGGED_ELEMENTS = new HashMap<>();
    public static final String HANDLERS = "handlers";

    static {
        TAGGED_ELEMENTS.put(MergePredicates.MERGE_PREDICATES, MergePredicates::new);
        TAGGED_ELEMENTS.put(MergeLists.MERGE_LISTS, MergeLists::new);
        TAGGED_ELEMENTS.put(MergeValidation.MERGE_VALIDATION, MergeValidation::new);
        TAGGED_ELEMENTS.put(ForbiddenPredicates.FORBIDDEN_PREDICATES, ForbiddenPredicates::new);
    }

    public HandlersSequence(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, AbstractHandler>> getTaggedElements() {
        return TAGGED_ELEMENTS;
    }
}
