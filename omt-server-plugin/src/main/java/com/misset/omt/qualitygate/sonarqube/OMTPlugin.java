package com.misset.omt.qualitygate.sonarqube;

import com.misset.omt.qualitygate.sonarqube.language.OMTLanguage;
import com.misset.omt.qualitygate.sonarqube.language.OMTLanguageProperties;
import com.misset.omt.qualitygate.sonarqube.language.OMTQualityProfile;
import com.misset.omt.qualitygate.sonarqube.repository.OMTRulesDefinition;
import com.misset.omt.qualitygate.sonarqube.sensor.OMTSensor;
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
