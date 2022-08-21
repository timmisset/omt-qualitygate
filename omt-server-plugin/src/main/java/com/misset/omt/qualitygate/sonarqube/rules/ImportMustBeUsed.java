package com.misset.omt.qualitygate.sonarqube.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

@Rule(key = Rules.Keys.IMPORT_MUST_BE_USED, name = Rules.Keys.IMPORT_MUST_BE_USED)
public class ImportMustBeUsed implements SonarRule {
    @RuleProperty(key = Rules.Attributes.IMPORT_MUST_BE_USED_EXCLUSION, description = "Exclude certain files (by name) from this check", defaultValue = "index.omt")
    String exclusion;
}
