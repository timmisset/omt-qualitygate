package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.rules.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = VariableMustBeUsed.NAME,
        name = VariableMustBeUsed.NAME)
public class VariableMustBeUsed implements OMTCheck {
    public static final String NAME = "VariableMustBeUsed";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

}
