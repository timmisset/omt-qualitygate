package com.misset.omt.qualitygate.runner.sensor;

import com.misset.omt.qualitygate.analyzer.context.OMTSensorContext;
import com.misset.omt.qualitygate.analyzer.issue.OMTIssue;
import com.misset.omt.qualitygate.analyzer.rules.Rule;
import com.misset.omt.qualitygate.analyzer.rules.Rules;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * When running locally, we don't have any information about the server at hand. Might be an improvement
 * to create a server/project binding configuration to do obtain that information but that's not currently
 * supported. Therefore, all rules are considered active.
 */
public class OMTSensorContextImpl implements OMTSensorContext {

    private final File file;
    private final String content;

    private final List<OMTIssue> issues = new ArrayList<>();

    public OMTSensorContextImpl(File file) {
        this.file = file;
        try {
            this.content = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return this.file;
    }

    @Override
    public List<String> getActiveRules() {
        return Rules.getRules().stream().map(Rule::getKey).collect(Collectors.toList());
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getFilename() {
        return file.getName();
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
