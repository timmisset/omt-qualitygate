package com.misset.omt.qualitygate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TreeUtil {

    /**
     * Recursively collects all children for this OMTElement
     */
    public static Collection<OMTElement> getAllChildren(OMTElement element) {
        Collection<OMTElement> children = new ArrayList<>(element.getChildren());
        List<OMTElement> grandChildren = children.stream().map(TreeUtil::getAllChildren).flatMap(Collection::stream).collect(Collectors.toList());
        children.addAll(grandChildren);
        return children;
    }

    /**
     * Returns a list with all the child elements (recursively) of this OMTElement of the specific class
     */
    public static <T extends OMTElement> Collection<T> findChildren(OMTElement element, Class<T> elementClass) {
        return getAllChildren(element).stream().filter(elementClass::isInstance)
                .map(elementClass::cast)
                .collect(Collectors.toList());
    }

    public static <T extends OMTElement> Optional<T> findChild(OMTElement element, Class<T> elementClass) {
        return getAllChildren(element).stream().filter(elementClass::isInstance)
                .map(elementClass::cast)
                .findFirst();
    }

    public static boolean isAncestor(OMTElement ancestor, OMTElement element) {
        return getAllChildren(ancestor).contains(element);
    }

}
