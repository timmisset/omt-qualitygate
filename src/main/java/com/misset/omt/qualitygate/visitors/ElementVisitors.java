package com.misset.omt.qualitygate.visitors;

import java.util.List;

import com.misset.omt.qualitygate.model.OMTElement;

public class ElementVisitors {
    public static final List<AbstractElementVisitor<? extends OMTElement>> ALL_VISITORS = List.of(
            StrictShorthandedMapVisitor.INSTANCE,
            ModelItemVisitor.INSTANCE,
            OMTFileVisitor.INSTANCE,
            VariableVisitor.INSTANCE
    );
}
