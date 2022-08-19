package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.repository.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = PrefixMustBeUsed.NAME, name = PrefixMustBeUsed.NAME)
public class PrefixMustBeUsed implements OMTRule {
    public static final String NAME = "PrefixMustBeUsed";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

    public static final String PRIMARY_MESSAGE = "Remove this unused prefix";
}
