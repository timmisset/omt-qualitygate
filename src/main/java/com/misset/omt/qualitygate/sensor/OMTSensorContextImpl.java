package com.misset.omt.qualitygate.sensor;

import com.misset.omt.qualitygate.issue.OMTIssue;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.sensor.SensorContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OMTSensorContextImpl implements OMTSensorContext {

    private final SensorContext context;
    private final InputFile inputFile;
    private final List<OMTIssue> issues = new ArrayList<>();

    public OMTSensorContextImpl(SensorContext context, InputFile inputFile) {
        this.context = context;
        this.inputFile = inputFile;
    }

    @Override
    public ActiveRules getActiveRules() {
        return context.activeRules();
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
}
