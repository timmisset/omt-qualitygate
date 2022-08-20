package rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.StrictShorthandedMapVisitor;
import com.misset.omt.qualitygate.model.OMTElement;
import org.junit.jupiter.api.Test;

public class ShorthandsShouldBeUsedTest extends RuleTest {
    @Override
    protected String getRule() {
        return Rules.Keys.SHORTHANDS_SHOULD_BE_USED;
    }

    @Override
    protected AbstractElementVisitor<? extends OMTElement> getVisitor() {
        return StrictShorthandedMapVisitor.INSTANCE;
    }

    @Test
    void testHasIssueWhenShorthandCouldBeUsed() {
        String content = "" +
                "model:\n" +
                "   Activity: !Activity\n" +
                "       variables:\n" +
                "       - name: $naam";
        assertHasIssue(content);
    }

    @Test
    void testHasIssueWhenShorthandCouldBeUsedWithMultipleKeys() {
        String content = "" +
                "model:\n" +
                "   Activity: !Activity\n" +
                "       variables:\n" +
                "       -   name: $naam\n" +
                "           value: 'test'\n";
        assertHasIssue(content);
    }

    @Test
    void testHasNoIssueWhenShorthandCouldNotBeUsed() {
        String content = "" +
                "model:\n" +
                "   Activity: !Activity\n" +
                "       variables:\n" +
                "       -   name: $naam\n" +
                "           value: 'test'\n" +
                "           readOnly: true\n";
        assertNoIssues(content);
    }

    @Test
    void testHasNoIssueWhenShorthandIsUsed() {
        String content = "" +
                "model:\n" +
                "   Activity: !Activity\n" +
                "       variables:\n" +
                "       - $naam\n";
        assertNoIssues(content);
    }

    @Test
    void testHasNoIssueWhenRuleIsDisabled() {
        String content = "" +
                "model:\n" +
                "   Activity: !Activity\n" +
                "       variables:\n" +
                "       - name: $naam";
        disableRule();
        assertNoIssues(content);
    }
}
