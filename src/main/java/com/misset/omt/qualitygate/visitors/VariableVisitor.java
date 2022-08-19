package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.model.maps.variables.DeclaredVariable;
import com.misset.omt.qualitygate.rules.VariableNameMustStartWithSymbol;

public class VariableVisitor extends AbstractElementVisitor<DeclaredVariable> {

    public static final VariableVisitor INSTANCE = new VariableVisitor();

    private VariableVisitor() {
        super(DeclaredVariable.class);
    }

    @Override
    void visit(DeclaredVariable element) {
        String name = element.getName();
        if(isActive(VariableNameMustStartWithSymbol.KEY) && name != null && !name.startsWith("$")) {
            newIssue(VariableNameMustStartWithSymbol.KEY, element, "Variable must start with $-symbol");
        }
    }
}
