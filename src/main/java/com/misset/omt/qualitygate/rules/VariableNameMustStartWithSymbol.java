package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.repository.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = VariableNameMustStartWithSymbol.NAME,
        name = VariableNameMustStartWithSymbol.NAME)
public class VariableNameMustStartWithSymbol implements OMTRule {
    public static final String NAME = "VariableNameMustStartWithSymbol";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

}
