package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.OMTSensorTest;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.visitors.ElementVisitor;
import com.misset.omt.qualitygate.visitors.OMTFileVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

import static org.mockito.Mockito.when;

class ImportMustBeUsedTest extends OMTSensorTest {

    @Override
    protected RuleKey getRule() {
        return ImportMustBeUsed.KEY;
    }

    @Override
    protected ElementVisitor<? extends OMTElement> getVisitor() {
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
        runValidation("index.omt", content, OMTFileType.DEFAULT);
        runValidation("anotherFile.omt", content, OMTFileType.DEFAULT);
        assertNoIssues();
        runValidation("not-excluded.omt", content, OMTFileType.DEFAULT);
        assertHasIssue();
    }

}
