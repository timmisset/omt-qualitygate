package com.misset.omt.qualitygate.sonarqube.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import org.sonar.check.Rule;

@Rule(key = Rules.Keys.VARIABLE_MUST_BE_USED,
        name = Rules.Keys.VARIABLE_MUST_BE_USED)
public class VariableMustBeUsed implements SonarRule {
}
