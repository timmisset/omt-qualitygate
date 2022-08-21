package com.misset.omt.qualitygate.sonarqube.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import org.sonar.check.Rule;

@Rule(key = Rules.Keys.VARIABLE_NAME_MUST_START_WITH_SYMBOL,
        name = Rules.Keys.VARIABLE_NAME_MUST_START_WITH_SYMBOL)
public class VariableNameMustStartWithSymbol implements SonarRule {
}
