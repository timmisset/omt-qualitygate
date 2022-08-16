package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.OMTSensorTest;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.visitors.ElementVisitor;
import com.misset.omt.qualitygate.visitors.ModelItemVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

class VariableMustBeUsedTest extends OMTSensorTest {

    @Override
    protected RuleKey getRule() {
        return VariableMustBeUsed.KEY;
    }

    @Override
    protected ElementVisitor<? extends OMTElement> getVisitor() {
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
