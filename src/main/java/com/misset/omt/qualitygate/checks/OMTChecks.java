package com.misset.omt.qualitygate.checks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Container class that registers all OMTChecks of the plugin
 */
public class OMTChecks {

    private OMTChecks() {

    }

    private static final Map<String, Class<? extends OMTCheck>> ACTIVE_CHECKS = new HashMap<>();
    private static final Map<String, Class<? extends OMTCheck>> INACTIVE_CHECKS = new HashMap<>();
    private static final Map<String, Class<? extends OMTCheck>> ALLCHECKS = new HashMap<>();
    static {
        // Add checks that should be enabled by default
        ACTIVE_CHECKS.put(ImportMustBeUsed.NAME, ImportMustBeUsed.class);
        ACTIVE_CHECKS.put(UtilsShouldBeKeptSeparate.NAME, UtilsShouldBeKeptSeparate.class);
        ACTIVE_CHECKS.put(VariableNameMustStartWithSymbol.NAME, VariableNameMustStartWithSymbol.class);
        ACTIVE_CHECKS.put(VariableMustBeUsed.NAME, VariableMustBeUsed.class);

        // Add checks that should be disabled by default. Why even have them? Who knows!
        // ...

        ALLCHECKS.putAll(ACTIVE_CHECKS);
        ALLCHECKS.putAll(INACTIVE_CHECKS);
    }

    public static Collection<Class<? extends OMTCheck>> getAllChecksByClass() {
        return ACTIVE_CHECKS.values();
    }

    public static Collection<String> getActiveChecksByName() {
        return ACTIVE_CHECKS.keySet();
    }

}
