package com.misset.omt.qualitygate.model.sequences;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.tags.TaggedValuesContainer;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

public abstract class AbstractTaggedValuesSequence<T extends OMTElement> extends AbstractSequence implements TaggedValuesContainer<T> {
    protected AbstractTaggedValuesSequence(Node node) {
        super(node);
    }

    protected abstract HashMap<String, Function<Node, T>> getTaggedElements();

    @Override
    protected OMTElement parseSequenceItemNode(Node sequenceItem) {
        return Optional.ofNullable(sequenceItem.getTag())
                .map(Tag::getValue)
                .map(s -> getTaggedElements().getOrDefault(s, node -> null).apply(sequenceItem))
                .orElse(null);
    }

    @Override
    public Collection<String> getExpectedTags() {
        return getTaggedElements().keySet();
    }
}
