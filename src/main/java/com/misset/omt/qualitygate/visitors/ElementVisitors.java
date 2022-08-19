package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.model.OMTElement;

import java.util.List;

public class ElementVisitors {
    public static final List<AbstractElementVisitor<? extends OMTElement>> ALL_VISITORS = List.of(
            StrictShorthandedMapVisitor.INSTANCE,
            ModelItemVisitor.INSTANCE,
            OMTFileVisitor.INSTANCE,
            VariableVisitor.INSTANCE
    );
}
