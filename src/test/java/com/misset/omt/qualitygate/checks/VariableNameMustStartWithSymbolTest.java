package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.OMTSensorTest;
import com.misset.omt.qualitygate.visitor.ElementVisitor;
import com.misset.omt.qualitygate.visitor.VariableVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

class VariableNameMustStartWithSymbolTest extends OMTSensorTest {

    @Override
    protected RuleKey getRule() {
        return VariableNameMustStartWithSymbol.KEY;
    }

    @Override
    protected ElementVisitor getVisitor() {
        return VariableVisitor.INSTANCE;
    }

    @Test
    void testValidateElementHasNoViolations() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - $shorthand = 'test'";
        assertNoIssues(content);
    }

    @Test
    void testValidateElementHasNoStructureViolations() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name: $name\n" +
                "         value: 'test'\n";
        assertNoIssues(content);
    }

    @Test
    void testValidateElementHasNameViolation() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name: name\n" +
                "         value: 'test'\n";
        assertIssue(content);
    }

    @Test
    void testValidateElementHasNameViolationWhenShorthanded() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name = 'test'\n";
        assertIssue(content);
    }

    @Test
    void testValidateElementHasNameViolationWhenRuleNotActive() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name: name\n" +
                "         value: 'test'\n";
        disableRule();
        assertNoIssues(content);
    }

}
