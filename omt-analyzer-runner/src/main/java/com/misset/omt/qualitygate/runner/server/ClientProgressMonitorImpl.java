package com.misset.omt.qualitygate.runner.server;

import org.sonarsource.sonarlint.core.commons.progress.ClientProgressMonitor;

/**
 * Sonarqube ServerApi wishes to report on specific events when calling the server.
 * We don't really care for that so we simply leave all implementations void;
 */
public class ClientProgressMonitorImpl implements ClientProgressMonitor {
    @Override
    public void setMessage(String msg) {
        /* noop */
    }

    @Override
    public void setFraction(float fraction) {
        /* noop */
    }

    @Override
    public void setIndeterminate(boolean indeterminate) {
        /* noop */
    }
}
