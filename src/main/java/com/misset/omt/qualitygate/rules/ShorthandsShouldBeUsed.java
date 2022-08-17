package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.repository.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = ShorthandsShouldBeUsed.NAME,
        name = ShorthandsShouldBeUsed.NAME)
public class ShorthandsShouldBeUsed implements OMTRule {
    public static final String NAME = "ShorthandsShouldBeUsed";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);
}
