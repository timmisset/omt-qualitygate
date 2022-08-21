package rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.VariableVisitor;
import com.misset.omt.qualitygate.model.OMTElement;
import org.junit.jupiter.api.Test;

class VariableNameMustStartWithSymbolTest extends SonarRuleTest {

    @Override
    protected String getRule() {
        return Rules.Keys.VARIABLE_NAME_MUST_START_WITH_SYMBOL;
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
