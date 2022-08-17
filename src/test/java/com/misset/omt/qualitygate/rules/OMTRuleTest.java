package com.misset.omt.qualitygate.rules;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.visitors.ElementVisitors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;

@ExtendWith(MockitoExtension.class)
public abstract class OMTRuleTest {

    @Mock
    protected SensorContext context;

    @Mock
    protected ActiveRules activeRules;

    @Mock
    protected ActiveRule activeRule;

    @Mock
    protected NewIssue newIssue;

    @Mock
    protected NewIssueLocation newIssueLocation;

    @Mock
    protected TextRange textRange;

    @Mock
    InputFile inputFile;

    protected abstract RuleKey getRule();

    protected abstract AbstractElementVisitor<? extends OMTElement> getVisitor();

    @BeforeEach
    void setUp() {
        /*
            These generic stubs are required by all positive assertions made by the tests and are often not touched
            by the negative assertions. For this reason, lenient() is acceptable to keep this generic setup useful
            and prevent having to explicitly enable the mocks per test.
         */
        lenient().when(context.activeRules()).thenReturn(activeRules);
        lenient().when(activeRules.find(eq(getRule()))).thenReturn(activeRule);
        lenient().when(context.newIssue()).thenReturn(newIssue);
        lenient().when(newIssue.newLocation()).thenReturn(newIssueLocation);
        lenient().when(inputFile.newRange(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(textRange);

        assertTrue(ElementVisitors.ALL_VISITORS.contains(getVisitor()),
                "Test subject: " + getVisitor().getClass().getName() + " is not registered as visitor");

    }

    protected void disableRule() {
        when(activeRules.find(eq(getRule()))).thenReturn(null);
    }

    protected void runValidation(String content) {
        runValidation(content, OMTFileType.DEFAULT);
    }

    protected void runValidation(String content, OMTFileType type) {
        runValidation("file." + type.getExtension(), content);
    }

    protected void runValidation(String filename, String content) {
        try {
            when(inputFile.contents()).thenReturn(content);
            when(inputFile.filename()).thenReturn(filename);
            getVisitor().visitElements(context, inputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void assertNoIssues(String content) {
        runValidation(content);
        assertNoIssues();
    }



    protected void assertNoIssues() {
        verify(context, never()).newIssue();
    }


    protected void assertHasIssue(String content) {
        assertHasIssue(content, times(1));
    }

    /**
     * Asserts that a new Issue was created for the RuleKey of this test class
     */
    protected void assertHasIssue(String content, VerificationMode mode) {
        runValidation(content);
        assertHasIssue(mode);
    }

    protected void assertHasIssue() {
        assertHasIssue(times(1));
    }

    protected void assertHasIssue(VerificationMode mode) {
        verify(context, mode).newIssue();
        verify(newIssue, mode).forRule(getRule());
    }

}
