package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.checks.OMTCheck;
import com.misset.omt.qualitygate.checks.VariableNameMustStartWithSymbol;
import com.misset.omt.qualitygate.language.OMTLanguage;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.parser.OMTParser;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.rule.Checks;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.rule.RuleKey;

import java.io.IOException;

public class OMTSensor implements Sensor {

    private final Checks<OMTCheck> checks;

    private final OMTParser parser = new OMTParser();

    public OMTSensor(CheckFactory checkFactory) {
        checks = checkFactory.create(OMTRepository.REPOSITORY_KEY);
        checks.addAnnotatedChecks(
                VariableNameMustStartWithSymbol.class
        );
    }

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("OMT Sensor");
        descriptor.onlyOnLanguage(OMTLanguage.KEY);
    }

    @Override
    public void execute(SensorContext context) {
        FilePredicate filter = context.fileSystem().predicates().hasLanguage(OMTLanguage.KEY);
        context.fileSystem().inputFiles(filter).forEach(file -> checkFile(context, file));
    }

    private void checkFile(SensorContext context, InputFile file) {
        try {
            OMTFile omtFile = parser.process(file.contents(), parser.getFileType(file.filename()));
            omtFile.validate(context, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isActive(SensorContext context, RuleKey key) {
        return context.activeRules().find(key) != null;
    }
}
