package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.rules.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = VariableNameMustStartWithSymbol.NAME,
        name = VariableNameMustStartWithSymbol.NAME)
public class VariableNameMustStartWithSymbol implements OMTCheck {
    public static final String NAME = "VariableNameMustStartWithSymbol";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

}
