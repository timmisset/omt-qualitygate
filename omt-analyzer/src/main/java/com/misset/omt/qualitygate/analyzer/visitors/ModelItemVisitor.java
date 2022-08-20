package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.validators.PrefixUsageValidator;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.maps.variables.DeclaredVariable;

public class ModelItemVisitor extends AbstractElementVisitor<ModelItem> {

    public static final ModelItemVisitor INSTANCE = new ModelItemVisitor();

    private ModelItemVisitor() {
        super(ModelItem.class);
    }

    @Override
    public void visit(ModelItem modelItem) {
        // validate variable usages
        TreeUtil.findChildren(modelItem, DeclaredVariable.class)
                .stream()
                .filter(variable -> !ScalarVisitorUtil.containsValue(modelItem, variable, variable.getName()))
                .forEach(variable -> newIssue(Rules.Keys.VARIABLE_MUST_BE_USED, variable, variable.getName()));

        // validate prefix usages
        validateIfActive(Rules.Keys.PREFIX_MUST_BE_USED, new PrefixUsageValidator<>(this));
    }
}
