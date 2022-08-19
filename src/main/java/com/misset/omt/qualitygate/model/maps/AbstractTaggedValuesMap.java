package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.tags.TaggedValuesContainer;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

/**
 * An TaggedValuesMap expects all entries to be tagged to determine the type of entry
 */
public abstract class AbstractTaggedValuesMap<T extends OMTElement> extends AbstractMap implements TaggedValuesContainer<T> {
    protected AbstractTaggedValuesMap(Node node) {
        super(node);
    }

    protected abstract HashMap<String, Function<Node, T>> getTaggedElements();

    @Override
    protected OMTElement parseNode(String key, Node valueNode) {
        return Optional.ofNullable(valueNode.getTag())
                .map(Tag::getValue)
                .map(s -> getTaggedElements().getOrDefault(s, node -> null).apply(valueNode))
                .orElse(null);
    }

    public Collection<String> getExpectedTags() {
        return getTaggedElements().keySet();
    }
}
