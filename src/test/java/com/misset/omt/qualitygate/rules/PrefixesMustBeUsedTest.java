package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.visitors.OMTFileVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

class PrefixesMustBeUsedTest extends OMTRuleTest {

    @Override
    protected RuleKey getRule() {
        return PrefixMustBeUsed.KEY;
    }

    @Override
    protected AbstractElementVisitor<? extends OMTElement> getVisitor() {
        return OMTFileVisitor.INSTANCE;
    }

    @Test
    void testValidateHasIssueWhenNotUsed() {
        String content = "prefixes:\n" +
                "   prefix: namespace";
        assertHasIssue(content);
    }

    @Test
    void testValidateHasIssueNoWhenUsed() {
        String content = "" +
                "prefixes:\n" +
                "   prefix: namespace\n" +
                "model:\n" +
                "   MyActivity: !Activity\n" +
                "       title: prefix:localName\n";
        assertNoIssues(content);
    }

    @Test
    void testValidateHasIssueNoWhenUsedAsOntologyPrefix() {
        String content = "" +
                "prefixes:\n" +
                "   prefix: namespace\n" +
                "model:\n" +
                "   Ontology: !Ontology\n" +
                "       prefix: prefix\n";
        assertNoIssues(content);
    }
}
