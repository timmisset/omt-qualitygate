package com.misset.omt.qualitygate.model;

import org.yaml.snakeyaml.nodes.Node;

import java.util.Collection;

/**
 * Base class for all Yaml nodes that are parsed to specific OMTElements
 */
public interface OMTElement {

    /**
     * Returns a list with all the direct child elements of this OMTElement
     */
    Collection<OMTElement> getChildren();

    /**
     * Returns the underlying YAML node that contains source information about the OMT element
     * such as the location information, node type etc.
     */
    Node getNode();
}
