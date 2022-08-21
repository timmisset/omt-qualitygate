package com.misset.omt.qualitgate.analyzer.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.OMTFileVisitor;
import com.misset.omt.qualitygate.model.OMTElement;
import org.junit.jupiter.api.Test;

class UtilsShouldBeKeptSeparateTest extends RuleTest {

    @Override
    protected String getRule() {
        return Rules.Keys.UTILS_SHOULD_BE_KEPT_SEPARATE;
    }

    @Override
    protected AbstractElementVisitor<? extends OMTElement> getVisitor() {
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
