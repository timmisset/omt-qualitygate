package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.model.maps.variables.DeclaredVariable;

public class VariableVisitor extends AbstractElementVisitor<DeclaredVariable> {

    public static final VariableVisitor INSTANCE = new VariableVisitor();

    private VariableVisitor() {
        super(DeclaredVariable.class);
    }

    @Override
    public void visit(DeclaredVariable element) {
        String name = element.getName();
        if (isActive(Rules.Keys.VARIABLE_NAME_MUST_START_WITH_SYMBOL) && name != null && !name.startsWith("$")) {
            newIssue(Rules.Keys.VARIABLE_NAME_MUST_START_WITH_SYMBOL, element, "$" + name);
        }
    }
}
