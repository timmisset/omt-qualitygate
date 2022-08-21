package com.misset.omt.qualitygate.runner.sensor;

import org.sonarsource.sonarlint.core.serverapi.rules.ServerActiveRule;

import java.util.Collection;
import java.util.Map;

public class OMTRulePropertySetter {

    public static void setRuleProperties(OMTSensorContextImpl sensorContext, Collection<ServerActiveRule> activeRules) {
        activeRules.forEach(
                activeRule -> setRuleProperties(sensorContext, activeRule)
        );
    }

    private static void setRuleProperties(OMTSensorContextImpl sensorContext, ServerActiveRule rule) {
        String key = rule.getRuleKey().substring(4); // without the omt: prefix
        Map<String, String> params = rule.getParams();
        params.forEach((property, value) -> sensorContext.setPropertyValue(key, property, value));
    }

}
