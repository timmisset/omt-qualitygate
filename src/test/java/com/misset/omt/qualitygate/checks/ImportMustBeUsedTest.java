package com.misset.omt.qualitygate.checks;

import static org.mockito.Mockito.when;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.rules.ImportMustBeUsed;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.visitors.OMTFileVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

class ImportMustBeUsedTest extends OMTRuleTest {

    @Override
    protected RuleKey getRule() {
        return ImportMustBeUsed.KEY;
    }

    @Override
    protected AbstractElementVisitor<? extends OMTElement> getVisitor() {
        return OMTFileVisitor.INSTANCE;
    }

    @Test
    void testValidateHasIssueWhenNotUsed() {
        String content =
                "import:\n" +
                        "   myFile.omt:\n" +
                        "   - myImportedMember\n";
        assertHasIssue(content);
    }

    @Test
    void testValidateHasNoIssueWhenUsed() {
        String content =
                "import:\n" +
                        "   myFile.omt:\n" +
                        "   - myImportedMember\n" +
                        "\n" +
                        "model:\n" +
                        "   MyActivity: !Activity\n" +
                        "       onStart: |\n" +
                        "           @myImportedMember();\n";
        assertNoIssues(content);
    }

    @Test
    void testValidateHasNoIssueWhenExcluded() {
        String content =
                "import:\n" +
                        "   myFile.omt:\n" +
                        "   - myImportedMember\n";
        when(activeRule.param(ImportMustBeUsed.EXCLUSION)).thenReturn("index.omt;anotherFile.omt");
        runValidation("index.omt", content);
        runValidation("anotherFile.omt", content);
        assertNoIssues();
        runValidation("not-excluded.omt", content);
        assertHasIssue();
    }

}
