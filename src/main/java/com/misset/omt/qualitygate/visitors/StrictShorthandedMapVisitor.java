package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictShorthandedMap;
import com.misset.omt.qualitygate.rules.ShorthandsShouldBeUsed;

public class StrictShorthandedMapVisitor extends AbstractElementVisitor<AbstractStrictShorthandedMap> {
    public static final AbstractElementVisitor<? extends OMTElement> INSTANCE = new StrictShorthandedMapVisitor();
    private StrictShorthandedMapVisitor() {
        super(AbstractStrictShorthandedMap.class);
    }

    @Override
    void visit(AbstractStrictShorthandedMap map) {
        if(isActive(ShorthandsShouldBeUsed.KEY) &&
                !map.isShorthanded() &&
                map.getShorthandedValueKeys().containsAll(map.getKeys())) {
            newIssue(ShorthandsShouldBeUsed.KEY, map, "Replace this map with a shorthand: " + map.getPattern().pattern());
        }
    }
}
