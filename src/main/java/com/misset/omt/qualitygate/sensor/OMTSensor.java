package com.misset.omt.qualitygate.sensor;

import com.misset.omt.qualitygate.issue.OMTIssue;
import com.misset.omt.qualitygate.language.OMTLanguage;
import com.misset.omt.qualitygate.repository.OMTRepository;
import com.misset.omt.qualitygate.visitors.ElementVisitors;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * The OMTSensor is used by Sonarqube to trigger on the presence of OMT files.
 * The Sensor will call visitElements on every registered visitor with the provided OMTFile
 * <p>
 * This essentially means that every OMT element is visited once per visitor. Therefor, visitors
 * are confined to react to specific types only and do multiple validations per type. This to prevent
 * iterating over the entire OMT tree once per Rule.
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
        LOGGER.debug("Number of Element visitors: " + ElementVisitors.ALL_VISITORS.size());
        OMTSensorContextImpl omtSensorContext = new OMTSensorContextImpl(sensorContext, inputFile);
        visitFile(omtSensorContext);
        populateSensorContext(sensorContext, omtSensorContext, inputFile);
    }


    public void visitFile(OMTSensorContext context) {
        ElementVisitors.ALL_VISITORS.forEach(elementVisitor -> elementVisitor.visitElements(context));
    }

    private void populateSensorContext(SensorContext sensorContext, OMTSensorContext omtSensorContext, InputFile inputFile) {
        omtSensorContext.getIssues().forEach(
                omtIssue -> populateSensorContext(sensorContext, omtIssue, inputFile)
        );
    }

    private void populateSensorContext(SensorContext sensorContext, OMTIssue issue, InputFile inputFile) {
        NewIssue newIssue = sensorContext.newIssue();
        newIssue.forRule(issue.getKey());
        NewIssueLocation newIssueLocation = newIssue.newLocation();
        newIssueLocation.message(issue.getLocation().getMessage());

        newIssueLocation.on(inputFile);
        newIssueLocation.at(issue.getLocation().toTextRange(inputFile));
        newIssue.at(newIssueLocation);

        // additional issues:
        issue.getAdditionalLocations().forEach(
                location -> {
                    NewIssueLocation additionalLocation = newIssue.newLocation().message(location.getMessage())
                            .at(issue.getLocation().toTextRange(inputFile))
                            .on(inputFile);
                    newIssue.addLocation(additionalLocation);
                }
        );
        newIssue.save();
    }
}
