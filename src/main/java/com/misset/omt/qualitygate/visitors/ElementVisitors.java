package com.misset.omt.qualitygate.visitors;

import java.util.ArrayList;
import java.util.List;

import com.misset.omt.qualitygate.model.OMTElement;

public class ElementVisitors {

    public static final List<AbstractElementVisitor<? extends OMTElement>> ALL_VISITORS = new ArrayList<>();
    static {
        ALL_VISITORS.add(OMTFileVisitor.INSTANCE);
        ALL_VISITORS.add(ModelItemVisitor.INSTANCE);
        ALL_VISITORS.add(VariableVisitor.INSTANCE);
    }

}
