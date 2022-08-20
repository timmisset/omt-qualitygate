package com.misset.omt.qualitygate.sonarqube.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;

@org.sonar.check.Rule(key = Rules.Keys.VARIABLE_MUST_BE_USED,
        name = Rules.Keys.VARIABLE_MUST_BE_USED)
public class UtilsShouldBeKeptSeparate implements Rule {
}
