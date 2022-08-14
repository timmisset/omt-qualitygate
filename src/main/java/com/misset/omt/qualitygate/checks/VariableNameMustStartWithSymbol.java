package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.rules.OMTRepository;
import com.misset.omt.qualitygate.rules.OMTSensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = VariableNameMustStartWithSymbol.NAME)
public class VariableNameMustStartWithSymbol implements OMTCheck {
    public static final String NAME = "VariableNameMustStartWithSymbol";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

    public static boolean isIssue(SensorContext context, String name) {
        return OMTSensor.isActive(context, KEY) && name != null && !name.startsWith("$");
    }

}
