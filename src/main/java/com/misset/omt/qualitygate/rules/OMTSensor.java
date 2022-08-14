package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.checks.OMTCheck;
import com.misset.omt.qualitygate.checks.VariableNameMustStartWithSymbol;
import com.misset.omt.qualitygate.language.OMTLanguage;
import com.misset.omt.qualitygate.visitor.ElementVisitor;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.rule.Checks;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;

import java.util.ArrayList;
import java.util.List;

public class OMTSensor implements Sensor {

    private static final List<ElementVisitor> visitors = new ArrayList<>();

    public static void extend(ElementVisitor elementVisitor) {
        visitors.add(elementVisitor);
    }

    public OMTSensor() { }

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
        visitors.forEach(elementVisitor -> elementVisitor.visitElements(context, file));
    }
}
