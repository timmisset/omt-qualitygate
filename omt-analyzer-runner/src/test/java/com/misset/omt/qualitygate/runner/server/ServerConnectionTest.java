package com.misset.omt.qualitygate.runner.server;

import org.junit.jupiter.api.Test;
import org.sonarsource.sonarlint.core.serverapi.rules.ServerActiveRule;

import java.util.Collection;

/**
 * Integration test that requires a valid key to be set.
 */
public class ServerConnectionTest {

    public static final String endpoint = "http://localhost:9000";
    public static final String key = "sqp_8f990a2b106283b2cc6d2ea83b15ab5f98117bf9";

    public static final String projectKey = "omt";

    @Test
    void getActiveRules() {
        ServerConnection serverConnection = new ServerConnection(endpoint, key, projectKey);
        Collection<ServerActiveRule> activeRules = serverConnection.getActiveRules();
        System.out.println(activeRules);
    }

}
