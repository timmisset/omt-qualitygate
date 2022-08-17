package com.misset.omt.qualitygate.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Container class that registers all OMTRules of the plugin
 */
public class OMTRules {

    private OMTRules() {

    }

    private static final Map<String, Class<? extends OMTRule>> ACTIVE_RULES = new HashMap<>();
    private static final Map<String, Class<? extends OMTRule>> INACTIVE_RULES = new HashMap<>();
    private static final Map<String, Class<? extends OMTRule>> ALL_RULES = new HashMap<>();
    static {
        // Add checks that should be enabled by default
        ACTIVE_RULES.put(ImportMustBeUsed.NAME, ImportMustBeUsed.class);
        ACTIVE_RULES.put(UtilsShouldBeKeptSeparate.NAME, UtilsShouldBeKeptSeparate.class);
        ACTIVE_RULES.put(VariableNameMustStartWithSymbol.NAME, VariableNameMustStartWithSymbol.class);
        ACTIVE_RULES.put(VariableMustBeUsed.NAME, VariableMustBeUsed.class);

        // Add checks that should be disabled by default. Why even have them? Who knows!
        // ...

        ALL_RULES.putAll(ACTIVE_RULES);
        ALL_RULES.putAll(INACTIVE_RULES);
    }

    public static Collection<Class<? extends OMTRule>> getAllChecksByClass() {
        return ALL_RULES.values();
    }

    public static Collection<String> getActiveChecksByName() {
        return ACTIVE_RULES.keySet();
    }

}
