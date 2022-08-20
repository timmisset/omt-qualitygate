package com.misset.omt.qualitygate.sonarqube.language;

import org.sonar.api.resources.AbstractLanguage;

public class OMTLanguage extends AbstractLanguage {
    public static final String KEY = "omt";
    public static final String NAME = "OMT";

    public OMTLanguage() {
        super(KEY, NAME);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getFileSuffixes() {
        return new String[] { "omt" };
    }

    @Override
    public boolean publishAllFiles() {
        return true;
    }
}
