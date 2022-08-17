package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.repository.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

@Rule(key = ImportMustBeUsed.NAME,
        name = ImportMustBeUsed.NAME)
public class ImportMustBeUsed implements OMTRule {
    public static final String NAME = "ImportMustBeUsed";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

    public static final String EXCLUSION = "exclusion";

    @RuleProperty(key = EXCLUSION, description = "Exclude certain files (by name) from this check", defaultValue = "index.omt")
    String exclusion;
}
