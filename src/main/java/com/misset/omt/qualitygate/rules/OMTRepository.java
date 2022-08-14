package com.misset.omt.qualitygate.rules;

import org.sonar.api.rule.RuleKey;

public class OMTRepository {

    public static final String REPOSITORY_KEY = "omt";
    public static final String REPOSITORY_NAME = "OMT Analyzer";

    public static RuleKey RULE_KEY(String rule) {
        return RuleKey.of(REPOSITORY_KEY, rule);
    }
}
