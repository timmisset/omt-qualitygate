package com.misset.omt.qualitygate.rules;

import java.util.stream.Collectors;

import com.misset.omt.qualitygate.language.OMTLanguage;
import com.misset.omt.qualitygate.visitors.ElementVisitors;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

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
        LOGGER.debug("number of Element visitors: " + ElementVisitors.ALL_VISITORS.size());
        LOGGER.debug(ElementVisitors.ALL_VISITORS.stream().map(elementVisitor -> elementVisitor.getClass().getName()).collect(Collectors.joining(", ")));
        ElementVisitors.ALL_VISITORS.forEach(elementVisitor -> elementVisitor.visitElements(context, file));
    }
}
