package com.misset.omt.qualitygate.runner.server;

import org.sonarsource.sonarlint.core.commons.progress.ProgressMonitor;
import org.sonarsource.sonarlint.core.serverapi.EndpointParams;
import org.sonarsource.sonarlint.core.serverapi.ServerApi;
import org.sonarsource.sonarlint.core.serverapi.qualityprofile.QualityProfile;
import org.sonarsource.sonarlint.core.serverapi.rules.ServerActiveRule;

import java.util.Collection;

public class ServerConnection {

    private final ServerApi serverApi;
    private final String projectKey;

    public ServerConnection(String endpoint, String token, String projectKey) {
        EndpointParams endpointParams = new EndpointParams(endpoint, false, null);
        serverApi = new ServerApi(endpointParams, new HttpClientImpl(token));
        this.projectKey = projectKey;
    }

    public Collection<ServerActiveRule> getActiveRules() {
        return serverApi.rules().getAllActiveRules(
                getActiveQualityProfile(), new ProgressMonitor(new ClientProgressMonitorImpl())
        );
    }

    public String getActiveQualityProfile() {
        return serverApi.qualityProfile().getQualityProfiles(projectKey)
                .stream()
                .filter(qualityProfile -> qualityProfile.getLanguage().equals("omt") && qualityProfile.isDefault())
                .map(QualityProfile::getKey)
                .findFirst()
                .orElse(null);
    }

}
