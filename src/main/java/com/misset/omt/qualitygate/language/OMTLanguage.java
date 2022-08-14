package com.misset.omt.qualitygate.language;

import org.sonar.api.resources.Language;

public class OMTLanguage implements Language {
    public static final String KEY = "OMT";
    public static final String[] FILE_SUFFIX = {"omt"};
    public static final String NAME = "OMT Language";

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
        return FILE_SUFFIX;
    }
}
