package com.misset.omt.qualitygate.sonarqube.rules;

import com.misset.omt.qualitygate.analyzer.rules.Rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Container class that registers all OMTRules of the plugin
 */
public class SonarRules {

    private static final Map<String, Class<? extends SonarRule>> ACTIVE_RULES = new HashMap<>();
    private static final Map<String, Class<? extends SonarRule>> INACTIVE_RULES = new HashMap<>();
    private static final Map<String, Class<? extends SonarRule>> ALL_RULES = new HashMap<>();

    static {
        // Add checks that should be enabled by default
        ACTIVE_RULES.put(Rules.Keys.IMPORT_MUST_BE_USED, ImportMustBeUsed.class);
        ACTIVE_RULES.put(Rules.Keys.SHORTHANDS_SHOULD_BE_USED, ShorthandsShouldBeUsed.class);
        ACTIVE_RULES.put(Rules.Keys.UTILS_SHOULD_BE_KEPT_SEPARATE, UtilsShouldBeKeptSeparate.class);
        ACTIVE_RULES.put(Rules.Keys.PREFIX_MUST_BE_USED, PrefixMustBeUsed.class);
        ACTIVE_RULES.put(Rules.Keys.VARIABLE_NAME_MUST_START_WITH_SYMBOL, VariableNameMustStartWithSymbol.class);
        ACTIVE_RULES.put(Rules.Keys.VARIABLE_MUST_BE_USED, VariableMustBeUsed.class);

        // Add checks that should be disabled by default. Why even have them? Who knows!
        // ...

        ALL_RULES.putAll(ACTIVE_RULES);
        ALL_RULES.putAll(INACTIVE_RULES);
    }

    private SonarRules() {

    }

    public static Collection<Class<? extends SonarRule>> getAllChecksByClass() {
        return ALL_RULES.values();
    }

    public static Collection<String> getActiveChecksByName() {
        return ACTIVE_RULES.keySet();
    }

}
