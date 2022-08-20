package com.misset.omt.qualitygate.sonarqube.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import org.sonar.check.RuleProperty;

@org.sonar.check.Rule(key = Rules.Keys.VARIABLE_MUST_BE_USED, name = Rules.Keys.VARIABLE_MUST_BE_USED)
public class ImportMustBeUsed implements Rule {
    @RuleProperty(key = Rules.Attributes.IMPORT_MUST_BE_USED_EXCLUSION, description = "Exclude certain files (by name) from this check", defaultValue = "index.omt")
    String exclusion;
}
