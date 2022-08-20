package com.misset.omt.qualitygate.model.maps.handlers;

import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import org.yaml.snakeyaml.nodes.Node;

public abstract class AbstractHandler extends AbstractStrictMap {

    protected static final String CONTEXT = "context";
    protected static final String PREDICATES = "predicates";
    protected static final String CURRENT = "current";
    protected static final String PARENT = "parent";
    protected static final String BOTH = "both";
    protected static final String SUBJECTS = "subjects";
    protected static final String ANY_PREDICATE = "anyPredicate";
    protected static final String WHEN = "when";
    protected static final String FROM = "from";
    protected static final String USE = "use";
    protected static final String TYPE = "type";
    protected static final String CREATE = "create";
    protected static final String DELETE = "delete";
    protected static final String UPDATE = "update";
    protected static final String QUERY = "query";

    protected AbstractHandler(Node node) {
        super(node);
    }
}
