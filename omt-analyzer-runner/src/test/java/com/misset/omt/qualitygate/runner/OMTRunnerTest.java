package com.misset.omt.qualitygate.runner;

import com.misset.omt.qualitygate.runner.server.ServerConnectionTest;
import org.junit.jupiter.api.Test;

class OMTRunnerTest {

    @Test
    void main() {

        OMTRunner.main(ServerConnectionTest.endpoint, ServerConnectionTest.projectKey, ServerConnectionTest.key);

    }
}
