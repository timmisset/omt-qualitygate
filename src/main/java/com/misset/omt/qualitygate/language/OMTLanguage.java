package com.misset.omt.qualitygate.language;

import org.sonar.api.resources.AbstractLanguage;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

public class OMTLanguage extends AbstractLanguage {
    private static final Logger LOGGER = Loggers.get(OMTLanguage.class);

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
