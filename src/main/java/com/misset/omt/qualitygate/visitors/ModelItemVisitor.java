package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.maps.variables.DeclaredVariable;
import com.misset.omt.qualitygate.rules.PrefixMustBeUsed;
import com.misset.omt.qualitygate.rules.VariableMustBeUsed;
import com.misset.omt.qualitygate.validators.PrefixUsageValidator;

public class ModelItemVisitor extends AbstractElementVisitor<ModelItem> {

    public static final ModelItemVisitor INSTANCE = new ModelItemVisitor();

    @Override
    protected Class<ModelItem> getOMTElementClass() {
        return ModelItem.class;
    }

    @Override
    void visit(ModelItem modelItem) {
        // validate variable usages
        TreeUtil.findChildren(modelItem, DeclaredVariable.class)
                .stream()
                .filter(variable -> !ScalarVisitorUtil.containsValue(modelItem, variable, variable.getName()))
                .forEach(variable -> newIssue(VariableMustBeUsed.KEY, variable));

        // validate prefix usages
        validateIfActive(PrefixMustBeUsed.KEY, new PrefixUsageValidator<>(this));
    }
}
