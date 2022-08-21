package com.misset.omt.qualitygate.runner;

import com.misset.omt.qualitygate.analyzer.visitors.ElementVisitors;
import com.misset.omt.qualitygate.runner.loader.OMTFileLoader;
import com.misset.omt.qualitygate.runner.reporter.IssueReporter;
import com.misset.omt.qualitygate.runner.sensor.OMTRulePropertySetter;
import com.misset.omt.qualitygate.runner.sensor.OMTSensorContextImpl;
import com.misset.omt.qualitygate.runner.server.ServerConnection;
import org.sonarsource.sonarlint.core.serverapi.rules.ServerActiveRule;

import java.io.File;
import java.util.Collection;
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
        } else {
            LOGGER.info("Discovered " + omtFiles.size() + " file(s) that will be scanned");
        }

        if (args.length != 3) {
            throw new RuntimeException("No arguments provided, expected 3 arguments: sonarqube server endpoint, project key and your user token");
        }

        ServerConnection serverConnection = new ServerConnection(args[0], args[2], args[1]);
        Collection<ServerActiveRule> activeRules = serverConnection.getActiveRules();
        if (activeRules.isEmpty()) {
            LOGGER.warning("No active rules discovered");
        } else {
            LOGGER.info(String.format("Total number of active rules on the server: %s", activeRules.size()));
        }

        List<OMTSensorContextImpl> sensorContexts = omtFiles.stream().map(file -> visitFile(file, activeRules)).collect(Collectors.toList());
        reporter.reportIssues(sensorContexts);
    }

    private static OMTSensorContextImpl visitFile(File file, Collection<ServerActiveRule> activeRules) {

        OMTSensorContextImpl omtSensorContext = new OMTSensorContextImpl(file, activeRules);
        // since we don't know anything about the rules configuration on the server, we must assume the default
        // attributes for rule properties are valid:
        OMTRulePropertySetter.setRuleProperties(omtSensorContext, activeRules);

        ElementVisitors.visitFile(omtSensorContext);
        return omtSensorContext;
    }

}
