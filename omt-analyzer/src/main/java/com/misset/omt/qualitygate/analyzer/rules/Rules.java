package com.misset.omt.qualitygate.analyzer.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Container class that registers all OMTRules of the plugin
 */
public class Rules {
    private static final List<Rule> RULE_LIST = new ArrayList<>();

    static {
        RULE_LIST.add(new RuleImpl(Keys.IMPORT_MUST_BE_USED, "Remove unused import: %s"));
        RULE_LIST.add(new RuleImpl(Keys.PREFIX_MUST_BE_USED, "Remove unused prefix: %s"));
        RULE_LIST.add(new RuleImpl(Keys.SHORTHANDS_SHOULD_BE_USED, "Replace this map structure with shorthand: %s"));
        RULE_LIST.add(new RuleImpl(Keys.UTILS_SHOULD_BE_KEPT_SEPARATE, "Move this utility section to a dedicated utilities file"));
        RULE_LIST.add(new RuleImpl(Keys.VARIABLE_MUST_BE_USED, "Remove unused variable: %s"));
        RULE_LIST.add(new RuleImpl(Keys.VARIABLE_NAME_MUST_START_WITH_SYMBOL, "Rename this variable to $%s"));
    }

    private Rules() {

    }

    public static void setActive(String ruleKey) {
        getRule(ruleKey).setActive();
    }

    public static Rule getRule(String ruleKey) {
        return RULE_LIST.stream().filter(rule -> ruleKey.equals(rule.getKey())).findFirst().orElseThrow(
                () -> new RuntimeException("Could not find rule with key: " + ruleKey)
        );
    }

    public static class Keys {

        public static final String IMPORT_MUST_BE_USED = "ImportMustBeUsed";
        public static final String PREFIX_MUST_BE_USED = "PrefixMustBeUsed";
        public static final String SHORTHANDS_SHOULD_BE_USED = "ShorthandsShouldBeUsed";
        public static final String UTILS_SHOULD_BE_KEPT_SEPARATE = "UtilsShouldBeKeptSeparate";
        public static final String VARIABLE_MUST_BE_USED = "VariableMustBeUsed";
        public static final String VARIABLE_NAME_MUST_START_WITH_SYMBOL = "VariableNameMustStartWithSymbol";
    }

    public static class Attributes {

        public static final String IMPORT_MUST_BE_USED_EXCLUSION = "exclusion";
    }

}
