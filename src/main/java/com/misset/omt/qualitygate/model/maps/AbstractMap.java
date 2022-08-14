package com.misset.omt.qualitygate.model.maps;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import org.yaml.snakeyaml.nodes.*;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractMap extends OMTBaseElement {

    private HashMap<String, OMTElement> mapping;

    protected AbstractMap(Node node) {
        super(node);
    }

    public Collection<OMTElement> getValues() {
        return mapping.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    protected  <T extends OMTElement> T get(String key, Class<T> asClass) {
        return asClass.cast(mapping.get(key));
    }

    protected boolean hasAny(String ... keys) {
        return Arrays.stream(keys).anyMatch(mapping::containsKey);
    }

    @Override
    public NodeId getExpectedNodeId() {
        return NodeId.mapping;
    }

    @Override
    protected void parseNode(Node node) {
        /*
         * parseNode is called during super class instantiation
         * Therefor, the creation of the mapping = new HashMap<>(); must be called within
         * this method and not when declaring or the constructor of this class
         */
        mapping = new HashMap<>();
        if (node instanceof MappingNode) {
            ((MappingNode) node).getValue().forEach(this::parseNodeTuple);
        }
    }

    private void parseNodeTuple(NodeTuple nodeTuple) {
        String key = getKey(nodeTuple.getKeyNode());
        mapping.put(key, parseNode(key, nodeTuple.getValueNode()));
    }

    protected abstract OMTElement parseNode(String key, Node valueNode);

    private String getKey(Node node) {
        if (node instanceof ScalarNode) {
            return ((ScalarNode) node).getValue();
        }
        return null;
    }

    @Override
    public Collection<OMTElement> getChildren() {
        return mapping.values();
    }
}
