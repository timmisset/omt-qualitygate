package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.checks.VariableMustBeUsed;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.maps.variables.DeclaredVariable;

public class ModelItemVisitor extends ElementVisitor<ModelItem> {

    public static final ModelItemVisitor INSTANCE = new ModelItemVisitor();

    @Override
    protected Class<ModelItem> getOMTElementClass() {
        return ModelItem.class;
    }

    @Override
    void visit(ModelItem modelItem) {
        TreeUtil.findChildren(modelItem, DeclaredVariable.class)
                .stream()
                .filter(variable -> !ScalarVisitor.isUsed(modelItem, variable, variable.getName()))
                .forEach(variable -> newIssue(VariableMustBeUsed.KEY, variable));
    }
}
