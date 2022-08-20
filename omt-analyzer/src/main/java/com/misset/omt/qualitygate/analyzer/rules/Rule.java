package com.misset.omt.qualitygate.analyzer.rules;

import java.util.HashMap;

/**
 *
 */
public interface Rule {

    String getKey();

    String getPrimaryMessage(String... arguments);

    String getValue(String property);

    void setProperties(HashMap<String, String> properties);

    void setActive();

    boolean isActive();

    void setPropertyValue(String property, String value);
}
