package com.misset.omt.qualitygate.language;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.misset.omt.qualitygate.repository.OMTRepository;
import com.misset.omt.qualitygate.rules.OMTRules;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

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
        verify(profile, times(OMTRules.getActiveChecksByName().size())).activateRule(eq(OMTRepository.REPOSITORY_KEY), anyString());
        verify(profile).done();
    }
}
