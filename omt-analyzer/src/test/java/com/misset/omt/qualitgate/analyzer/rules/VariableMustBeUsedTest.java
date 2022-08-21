package com.misset.omt.qualitgate.analyzer.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.ModelItemVisitor;
import com.misset.omt.qualitygate.model.OMTElement;
import org.junit.jupiter.api.Test;

class VariableMustBeUsedTest extends RuleTest {

    @Override
    protected String getRule() {
        return Rules.Keys.VARIABLE_MUST_BE_USED;
    }

    @Override
    protected AbstractElementVisitor<? extends OMTElement> getVisitor() {
        return ModelItemVisitor.INSTANCE;
    }

    @Test
    void testValidateHasIssueWhenNotUsed() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - $variable = 'test'";
        assertHasIssue(content);
    }

    @Test
    void testValidateHasIssueNoWhenUsed() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       title: $variable\n" +
                "       variables:\n" +
                "       - $variable = 'test'";
        assertNoIssues(content);
    }
}
