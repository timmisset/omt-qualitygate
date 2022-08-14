package com.misset.omt.qualitygate;

import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.parser.OMTParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public abstract class OMTSensorTest {

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
    }

    protected void disableRule() {
        when(activeRules.find(eq(getRule()))).thenReturn(null);
    }

    protected void runValidation(String content) {
        runValidation(content, OMTFileType.DEFAULT);
    }

    protected void runValidation(String content, OMTFileType type) {
        OMTFile omtFile = new OMTParser().process(content, type);
        omtFile.validate(context, inputFile);
    }

    protected void assertNoIssues(String content) {
        runValidation(content);
        assertNoIssues();
    }



    protected void assertNoIssues() {
        verify(context, never()).newIssue();
    }

    /**
     * Asserts that a new Issue was created for the RuleKey of this test class
     */
    protected void assertIssue(String content) {
        runValidation(content);
        assertIssue();
    }

    protected void assertIssue() {
        verify(context).newIssue();
        verify(newIssue).forRule(getRule());
    }

}
