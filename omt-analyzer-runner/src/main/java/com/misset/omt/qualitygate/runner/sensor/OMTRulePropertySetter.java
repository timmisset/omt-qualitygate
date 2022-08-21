package com.misset.omt.qualitygate.runner.sensor;

import com.misset.omt.qualitygate.analyzer.rules.Rules;

public class OMTRulePropertySetter {

    // TODO: make configurable or even better, extract from the Sonarqube server using a project binding
    public static void setRuleProperties(OMTSensorContextImpl sensorContext) {
        // currently, only 1 rule has properties
        sensorContext.setPropertyValue(Rules.Keys.IMPORT_MUST_BE_USED, Rules.Attributes.IMPORT_MUST_BE_USED_EXCLUSION, "index.omt");
    }

}
