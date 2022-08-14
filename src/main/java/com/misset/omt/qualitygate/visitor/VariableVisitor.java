package com.misset.omt.qualitygate.visitor;

import com.misset.omt.qualitygate.checks.VariableNameMustStartWithSymbol;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.variables.DeclaredVariable;
import com.misset.omt.qualitygate.rules.OMTSensor;

public class VariableVisitor extends ElementVisitor {

    public static final VariableVisitor INSTANCE = new VariableVisitor();
    static {
        OMTSensor.extend(INSTANCE);
    }

    @Override
    void visit(OMTElement element) {
        if(element instanceof DeclaredVariable) {
            validateVariableNameMustStartWithSymbol((DeclaredVariable) element);
        }
    }

    private void validateVariableNameMustStartWithSymbol(DeclaredVariable declaredVariable) {
        String name = declaredVariable.getName();
        if(isActive(VariableNameMustStartWithSymbol.KEY) && name != null && !name.startsWith("$")) {
            newIssue(VariableNameMustStartWithSymbol.KEY, declaredVariable);
        }
    }
}
