package com.misset.omt.qualitygate;

import com.misset.omt.qualitygate.language.OMTLanguage;
import com.misset.omt.qualitygate.language.OMTLanguageProperties;
import com.misset.omt.qualitygate.language.OMTQualityProfile;
import com.misset.omt.qualitygate.repository.OMTRulesDefinition;
import com.misset.omt.qualitygate.sensor.OMTSensor;
import org.sonar.api.Plugin;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

public class OMTPlugin implements Plugin {

    private static final Logger LOGGER = Loggers.get(OMTPlugin.class);

    @Override
    public void define(Context context) {
        // add the Language
        context.addExtensions(OMTLanguage.class, OMTQualityProfile.class);
        context.addExtension(OMTLanguageProperties.getProperties());

        context.addExtensions(OMTRulesDefinition.class, OMTSensor.class);

        LOGGER.info("Added all extensions");
    }
}
