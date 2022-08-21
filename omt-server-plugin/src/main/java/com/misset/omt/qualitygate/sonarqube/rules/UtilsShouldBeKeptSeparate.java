package com.misset.omt.qualitygate.sonarqube.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import org.sonar.check.Rule;

@Rule(key = Rules.Keys.UTILS_SHOULD_BE_KEPT_SEPARATE,
        name = Rules.Keys.UTILS_SHOULD_BE_KEPT_SEPARATE)
public class UtilsShouldBeKeptSeparate implements SonarRule {
}
