package com.misset.omt.qualitgate.analyzer.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.OMTFileVisitor;
import com.misset.omt.qualitygate.model.OMTElement;
import org.junit.jupiter.api.Test;

class ImportMustBeUsedTest extends RuleTest {

    @Override
    protected String getRule() {
        return Rules.Keys.IMPORT_MUST_BE_USED;
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
        Rules.getRule(Rules.Keys.IMPORT_MUST_BE_USED).setPropertyValue(Rules.Attributes.IMPORT_MUST_BE_USED_EXCLUSION, "index.omt;anotherFile.omt");
        runValidation("index.omt", content);
        runValidation("anotherFile.omt", content);
        assertNoIssues();
        runValidation("not-excluded.omt", content);
        assertHasIssue();
    }

}
