package com.misset.omt.qualitygate.language;

import com.misset.omt.qualitygate.repository.OMTRepository;
import com.misset.omt.qualitygate.rules.OMTRules;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * This class contains the QualityProfile "OMT Rules".
 * This will be the default set with active rules when the plugin is first installed on a Sonarqube server
 * <p>
 * A language requires at least 1 profile and this must be the default
 */
public final class OMTQualityProfile implements BuiltInQualityProfilesDefinition {

    private static final Logger LOGGER = Loggers.get(OMTQualityProfile.class);
    protected static final String OMT_RULES = "OMT Rules";

    @Override
    public void define(Context context) {
        NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile(OMT_RULES, OMTLanguage.KEY);
        profile.setDefault(true);

        OMTRules.getActiveChecksByName()
                .forEach(name -> profile.activateRule(OMTRepository.REPOSITORY_KEY, name));

        profile.done();
        LOGGER.debug(OMT_RULES + " profile is set");
    }

}
