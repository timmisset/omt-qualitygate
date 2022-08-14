package com.misset.omt.qualitygate.model.scalars;

import com.misset.omt.qualitygate.checks.VariableNameMustStartWithSymbol;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.yaml.snakeyaml.nodes.Node;


public class VariableNameStringElement extends StringElement {
    public VariableNameStringElement(Node node) {
        super(node);
    }

    public String getName() {
        return getNodeAsScalar().getValue();
    }

    @Override
    public void validateTree(SensorContext context, InputFile inputFile) {
        super.validateTree(context, inputFile);
        if(VariableNameMustStartWithSymbol.isIssue(context, getName())) {
            newIssue(context, VariableNameMustStartWithSymbol.KEY, inputFile);
        }
    }
}
