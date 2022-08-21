package rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.OMTFileVisitor;
import com.misset.omt.qualitygate.model.OMTElement;
import org.junit.jupiter.api.Test;

class PrefixesMustBeUsedTest extends SonarRuleTest {

    @Override
    protected String getRule() {
        return Rules.Keys.PREFIX_MUST_BE_USED;
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
