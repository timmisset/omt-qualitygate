package com.misset.omt.qualitygate.sonarqube.sensor;

import com.misset.omt.qualitygate.analyzer.context.OMTSensorContext;
import com.misset.omt.qualitygate.analyzer.issue.OMTIssue;
import com.misset.omt.qualitygate.analyzer.issue.OMTIssueLocation;
import com.misset.omt.qualitygate.analyzer.visitors.ElementVisitors;
import com.misset.omt.qualitygate.sonarqube.language.OMTLanguage;
import com.misset.omt.qualitygate.sonarqube.repository.OMTRepository;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * The OMTSensor is used by Sonarqube to trigger on the presence of OMT files.
 * The Sensor will create an OMTSensorContext from Sonarqubes SensorContext and then run it
 * against the ElementVisitors.
 */
public class OMTSensor implements Sensor {

    private static final Logger LOGGER = Loggers.get(OMTSensor.class);

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("OMT Sensor");
        descriptor.onlyOnLanguage(OMTLanguage.KEY);
        descriptor.createIssuesForRuleRepository(OMTRepository.REPOSITORY_KEY);
    }

    @Override
    public void execute(SensorContext context) {
        FilePredicate filter = context.fileSystem().predicates().hasLanguage(OMTLanguage.KEY);
        context.fileSystem().inputFiles(filter).forEach(file -> checkFile(context, file));
    }

    private void checkFile(SensorContext sensorContext, InputFile inputFile) {
        LOGGER.debug("OMTSensor check inputFile: " + inputFile.filename());
        LOGGER.debug("Number of Element com.misset.omt.qualitygate.analyzer.visitors: " + ElementVisitors.ALL_VISITORS.size());
        OMTSensorContextImpl omtSensorContext = new OMTSensorContextImpl(sensorContext, inputFile);
        ElementVisitors.visitFile(omtSensorContext);
        populateSensorContext(sensorContext, omtSensorContext, inputFile);
    }

    private void populateSensorContext(SensorContext sensorContext, OMTSensorContext omtSensorContext, InputFile inputFile) {
        omtSensorContext.getIssues().forEach(
                omtIssue -> populateSensorContext(sensorContext, omtIssue, inputFile)
        );
    }

    private void populateSensorContext(SensorContext sensorContext, OMTIssue issue, InputFile inputFile) {
        NewIssue newIssue = sensorContext.newIssue();
        ActiveRule activeRule = sensorContext.activeRules().findByInternalKey(
                OMTRepository.REPOSITORY_KEY, issue.getKey()
        );
        if (activeRule == null) {
            return;
        }

        newIssue.forRule(activeRule.ruleKey());
        NewIssueLocation newIssueLocation = newIssue.newLocation();
        newIssueLocation.message(issue.getLocation().getMessage());

        newIssueLocation.on(inputFile);
        newIssueLocation.at(getTextRange(issue.getLocation(), inputFile));
        newIssue.at(newIssueLocation);

        // additional issues:
        issue.getAdditionalLocations().forEach(
                location -> {
                    NewIssueLocation additionalLocation = newIssue.newLocation().message(location.getMessage())
                            .at(getTextRange(issue.getLocation(), inputFile))
                            .on(inputFile);
                    newIssue.addLocation(additionalLocation);
                }
        );
        newIssue.save();
    }

    private TextRange getTextRange(OMTIssueLocation issueLocation, InputFile inputFile) {
        return inputFile.newRange(issueLocation.getStartLine(),
                issueLocation.getStartLineOffset(),
                issueLocation.getEndLine(),
                issueLocation.getEndLineOffset());
    }
}
