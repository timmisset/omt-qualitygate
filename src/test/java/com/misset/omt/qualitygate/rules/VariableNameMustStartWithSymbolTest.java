package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.visitors.VariableVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

class VariableNameMustStartWithSymbolTest extends OMTRuleTest {

    @Override
    protected RuleKey getRule() {
        return VariableNameMustStartWithSymbol.KEY;
    }

    @Override
    protected AbstractElementVisitor<? extends OMTElement> getVisitor() {
        return VariableVisitor.INSTANCE;
    }

    @Test
    void testHasNoIssues() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - $shorthand = 'test'";
        assertNoIssues(content);
    }

    @Test
    void testWithoutValueHasNoIssues() {
        String content = "model:\n" +
                "    Activity: !Activity\n" +
                "        variables:\n" +
                "        - $variable";
        assertNoIssues(content);
    }

    @Test
    void testHasNoStructureIssues() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name: $name\n" +
                "         value: 'test'\n";
        assertNoIssues(content);
    }

    @Test
    void testHasNameViolation() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name: name\n" +
                "         value: 'test'\n";
        assertHasIssue(content);
    }

    @Test
    void testHasNameViolationWhenShorthanded() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name = 'test'\n";
        assertHasIssue(content);
    }

    @Test
    void testHasNameViolationWhenRuleNotActive() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name: name\n" +
                "         value: 'test'\n";
        disableRule();
        assertNoIssues(content);
    }

    @Test
    void testHasExactlyOneIssue() {
        String content = "model:\n" +
                "    Activity: !Activity\n" +
                "        variables:\n" +
                "        - $variable\n" +
                "        - name: $variable2\n" +
                "          value: 'test2'\n" +
                "        - parameter\n" +
                "\n" +
                "        params:\n" +
                "        - $unusedParameter\n" +
                "\n" +
                "        onStart: |\n" +
                "            @LOG($variable, parameter);\n";
        assertHasIssue(content);
    }

    @Test
    void testHasExactlyTwoIssues() {
        String content = "model:\n" +
                "    Activity: !Activity\n" +
                "        variables:\n" +
                "        - $variable\n" +
                "        - name: $variable2\n" +
                "          value: 'test2'\n" +
                "        - parameter\n" +
                "\n" +
                "        params:\n" +
                "        - $unusedParameter\n" +
                "\n" +
                "        onStart: |\n" +
                "            @LOG($variable, parameter);\n";
        assertHasIssue(content);
    }

}
