package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.checks.VariableNameMustStartWithSymbol;
import com.misset.omt.qualitygate.model.maps.variables.DeclaredVariable;

public class VariableVisitor extends ElementVisitor<DeclaredVariable> {

    public static final VariableVisitor INSTANCE = new VariableVisitor();

    @Override
    protected Class<DeclaredVariable> getOMTElementClass() {
        return DeclaredVariable.class;
    }

    @Override
    void visit(DeclaredVariable element) {
        String name = element.getName();
        if(isActive(VariableNameMustStartWithSymbol.KEY) && name != null && !name.startsWith("$")) {
            newIssue(VariableNameMustStartWithSymbol.KEY, element);
        }
    }
}
