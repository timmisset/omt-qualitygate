package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.OMTSensorTest;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.visitors.ElementVisitor;
import com.misset.omt.qualitygate.visitors.OMTFileVisitor;
import org.junit.jupiter.api.Test;
import org.sonar.api.rule.RuleKey;

class UtilsShouldBeKeptSeparateTest extends OMTSensorTest {

    @Override
    protected RuleKey getRule() {
        return UtilsShouldBeKeptSeparate.KEY;
    }

    @Override
    protected ElementVisitor<? extends OMTElement> getVisitor() {
        return OMTFileVisitor.INSTANCE;
    }

    @Test
    void testHasNoIssuesWhenOnlyModel() {
        String content = "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - $shorthand = 'test'";
        assertNoIssues(content);
    }

    @Test
    void testHasNoIssuesWhenOnlyRootCommands() {
        String content = "" +
                "commands: |\n" +
                "   DEFINE COMMAND command => {}\n";
        assertNoIssues(content);
    }

    @Test
    void testHasIssueWhenCombiningRootUtilsWithModelItem() {
        String content = "" +
                "commands: |\n" +
                "   DEFINE COMMAND command => {}\n" +
                "model:\n" +
                "   MyActivity: !Activity\n" +
                "       variables:\n" +
                "       - name: $name\n" +
                "         value: 'test'\n";
        assertHasIssue(content);
    }

    @Test
    void testHasNoIssueWhenCombiningModelItemUtilsWithModelItem() {
        String content = "" +
                "model:\n" +
                "   MyActivity: !Activity\n" +
                "       commands: |\n" +
                "           DEFINE COMMAND command => {}\n" +
                "       variables:\n" +
                "       - name: $name\n" +
                "         value: 'test'\n";
        assertNoIssues(content);
    }

    @Test
    void testHasNoIssueWhenCombiningRootUtilsWithModelUtils() {
        String content = "" +
                "commands: |\n" +
                "   DEFINE COMMAND command => {}\n" +
                "model:\n" +
                "   StandaloneQuery: !Query\n" +
                "       query:\n";
        assertNoIssues(content);
    }

    @Test
    void testHasIssueWhenCombiningModelItemWithModelUtils() {
        String content = "" +
                "model:\n" +
                "   Activity: !Activity\n" +
                "       onRun:\n" +
                "   StandaloneQuery: !StandaloneQuery\n" +
                "       query:\n";
        assertHasIssue(content);
    }

}
