package com.misset.omt.qualitygate.sonarqube.sensor;

import com.misset.omt.qualitygate.analyzer.context.OMTSensorContext;
import com.misset.omt.qualitygate.analyzer.issue.OMTIssue;
import com.misset.omt.qualitygate.analyzer.rules.Rules;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.rule.RuleKey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OMTSensorContextImpl implements OMTSensorContext {

    private final InputFile inputFile;
    private final List<OMTIssue> issues = new ArrayList<>();

    private final List<String> activeRules = new ArrayList<>();

    public OMTSensorContextImpl(SensorContext context, InputFile inputFile) {
        this.inputFile = inputFile;
        activeRules.addAll(
                context.activeRules().findAll().stream().map(ActiveRule::ruleKey).map(RuleKey::rule).collect(Collectors.toList())
        );
    }

    @Override
    public List<String> getActiveRules() {
        return activeRules;
    }

    @Override
    public String getContent() {
        try {
            return inputFile.contents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFilename() {
        return inputFile.filename();
    }

    @Override
    public void newIssue(OMTIssue issue) {
        issues.add(issue);
    }

    @Override
    public List<OMTIssue> getIssues() {
        return issues;
    }

    @Override
    public void setPropertyValue(String ruleKey, String property, String value) {
        Rules.getRule(ruleKey).setPropertyValue(property, value);
    }
}
