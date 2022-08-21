package com.misset.omt.qualitygate.runner.reporter;

import com.github.freva.asciitable.AsciiTable;
import com.misset.omt.qualitygate.analyzer.issue.OMTIssue;
import com.misset.omt.qualitygate.runner.sensor.OMTSensorContextImpl;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class IssueReporter {
    private final Logger logger;

    public IssueReporter(Logger logger) {
        this.logger = logger;
    }

    public void reportIssues(List<OMTSensorContextImpl> omtSensorContexts) {
        List<OMTSensorContextImpl> runsWithIssues = omtSensorContexts.stream().filter(omtSensorContext -> !omtSensorContext.getIssues().isEmpty())
                .collect(Collectors.toList());
        if (runsWithIssues.isEmpty()) {
            logger.info("No issues discovered! Well done!");
        }
        runsWithIssues.forEach(this::reportIssues);
    }

    private void reportIssues(OMTSensorContextImpl omtSensorContext) {
        List<OMTIssue> issues = omtSensorContext.getIssues();
        logger.warning(String.format("%s has %s issue(s)", omtSensorContext.getFile().getAbsolutePath(), issues.size()));

        String[] headers = new String[]{"Message", "Rule", "Start position", "End position"};
        String[][] rows = issues.stream().map(this::mapIssueToRow).collect(Collectors.toList()).toArray(String[][]::new);
        logger.warning(AsciiTable.getTable(headers, rows));
    }

    private String[] mapIssueToRow(OMTIssue issue) {
        return new String[]{
                issue.getLocation().getMessage(),
                issue.getKey(),
                issue.getLocation().getStartLine() + ":" + issue.getLocation().getStartLineOffset(),
                issue.getLocation().getEndLine() + ":" + issue.getLocation().getEndLineOffset()
        };
    }

}
