package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictShorthandedMap;

public class StrictShorthandedMapVisitor extends AbstractElementVisitor<AbstractStrictShorthandedMap> {
    public static final AbstractElementVisitor<? extends OMTElement> INSTANCE = new StrictShorthandedMapVisitor();
    private StrictShorthandedMapVisitor() {
        super(AbstractStrictShorthandedMap.class);
    }

    @Override
    public void visit(AbstractStrictShorthandedMap map) {
        if (isActive(Rules.Keys.SHORTHANDS_SHOULD_BE_USED) &&
                !map.isShorthanded() &&
                map.getShorthandedValueKeys().containsAll(map.getKeys())) {
            newIssue(Rules.Keys.SHORTHANDS_SHOULD_BE_USED, map, map.getPattern().pattern());
        }
    }
}
