package com.misset.omt.qualitygate.sensor;

import com.misset.omt.qualitygate.language.OMTLanguage;
import com.misset.omt.qualitygate.repository.OMTRepository;
import com.misset.omt.qualitygate.visitors.ElementVisitors;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
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

    private void checkFile(SensorContext context, InputFile file) {
        LOGGER.debug("OMTSensor check file: " + file.filename());
        LOGGER.debug("Number of Element visitors: " + ElementVisitors.ALL_VISITORS.size());
        ElementVisitors.ALL_VISITORS.forEach(elementVisitor -> elementVisitor.visitElements(context, file));
    }
}
