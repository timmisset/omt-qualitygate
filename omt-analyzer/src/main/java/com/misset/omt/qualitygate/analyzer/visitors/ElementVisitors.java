package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.analyzer.context.OMTSensorContext;
import com.misset.omt.qualitygate.model.OMTElement;

import java.util.List;

public class ElementVisitors {

    private ElementVisitors() {
    }

    public static final List<AbstractElementVisitor<? extends OMTElement>> ALL_VISITORS = List.of(
            StrictShorthandedMapVisitor.INSTANCE,
            ModelItemVisitor.INSTANCE,
            OMTFileVisitor.INSTANCE,
            VariableVisitor.INSTANCE
    );

    public static void visitFile(OMTSensorContext context) {
        ElementVisitors.ALL_VISITORS.forEach(elementVisitor -> elementVisitor.visitElements(context));
    }
}
