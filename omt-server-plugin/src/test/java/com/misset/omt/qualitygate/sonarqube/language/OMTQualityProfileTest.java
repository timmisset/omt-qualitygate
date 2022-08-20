package com.misset.omt.qualitygate.sonarqube.language;

import com.misset.omt.qualitygate.sonarqube.repository.OMTRepository;
import com.misset.omt.qualitygate.sonarqube.rules.SonarRules;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OMTQualityProfileTest {

    @Mock
    BuiltInQualityProfilesDefinition.NewBuiltInQualityProfile profile;

    @Mock
    BuiltInQualityProfilesDefinition.Context context;

    OMTQualityProfile omtQualityProfile = new OMTQualityProfile();

    @Test
    void define() {
        when(context.createBuiltInQualityProfile(OMTQualityProfile.OMT_RULES, OMTLanguage.KEY)).thenReturn(profile);
        omtQualityProfile.define(context);

        verify(context).createBuiltInQualityProfile(OMTQualityProfile.OMT_RULES, OMTLanguage.KEY);
        verify(profile, times(SonarRules.getActiveChecksByName().size())).activateRule(eq(OMTRepository.REPOSITORY_KEY), anyString());
        verify(profile).done();
    }
}
