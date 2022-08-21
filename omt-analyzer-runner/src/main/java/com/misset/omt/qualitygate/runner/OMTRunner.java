package com.misset.omt.qualitygate.runner;

import com.misset.omt.qualitygate.analyzer.visitors.ElementVisitors;
import com.misset.omt.qualitygate.runner.loader.OMTFileLoader;
import com.misset.omt.qualitygate.runner.reporter.IssueReporter;
import com.misset.omt.qualitygate.runner.sensor.OMTRulePropertySetter;
import com.misset.omt.qualitygate.runner.sensor.OMTSensorContextImpl;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OMTRunner {

    private static final Logger LOGGER = Logger.getLogger(OMTRunner.class.getName());
    private static final IssueReporter reporter = new IssueReporter(LOGGER);

    public static void main(String... args) {
        List<File> omtFiles = OMTFileLoader.getOMTFiles();
        if (omtFiles.isEmpty()) {
            LOGGER.warning("Could not find any omt files to run in: " + OMTFileLoader.getWorkingDir());
        }

        List<OMTSensorContextImpl> sensorContexts = omtFiles.stream().map(OMTRunner::visitFile).collect(Collectors.toList());
        reporter.reportIssues(sensorContexts);
    }

    private static OMTSensorContextImpl visitFile(File file) {
        OMTSensorContextImpl omtSensorContext = new OMTSensorContextImpl(file);
        // since we don't know anything about the rules configuration on the server, we must assume the default
        // attributes for rule properties are valid:
        OMTRulePropertySetter.setRuleProperties(omtSensorContext);

        ElementVisitors.visitFile(omtSensorContext);
        return omtSensorContext;
    }

}
