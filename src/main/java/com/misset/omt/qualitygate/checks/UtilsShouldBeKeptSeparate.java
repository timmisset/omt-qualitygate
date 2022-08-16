package com.misset.omt.qualitygate.checks;

import com.misset.omt.qualitygate.rules.OMTRepository;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = UtilsShouldBeKeptSeparate.NAME,
        name = UtilsShouldBeKeptSeparate.NAME)
public class UtilsShouldBeKeptSeparate implements OMTCheck {
    public static final String NAME = "UtilsShouldBeKeptSeparate";

    public static final RuleKey KEY = OMTRepository.RULE_KEY(NAME);

}
