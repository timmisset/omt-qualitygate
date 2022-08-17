package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.visitors.StrictShorthandedMapVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

public class ShorthandsShouldBeUsedTest extends OMTRuleTest {
    @Override
    protected RuleKey getRule() {
        return ShorthandsShouldBeUsed.KEY;
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
