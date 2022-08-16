package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.rules.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

@Rule(key = ImportMustBeUsed.NAME,
        name = ImportMustBeUsed.NAME)
public class ImportMustBeUsed implements OMTCheck {
    public static final String NAME = "ImportMustBeUsed";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

    public static final String EXCLUSION = "import.must.be.used.exclusion";

    @RuleProperty(key = EXCLUSION, description = "Exclude certain files (by name) from this check", defaultValue = "index.omt")
    String exclude;
}
