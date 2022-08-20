package com.misset.omt.qualitygate.analyzer.rules;

import java.util.HashMap;

public class RuleImpl implements Rule {

    private final String key;
    private final String primaryMessage;
    private HashMap<String, String> properties = new HashMap<>();

    private boolean active = false;

    protected RuleImpl(String key, String primaryMessage) {
        this.key = key;
        this.primaryMessage = primaryMessage;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public final String getValue(String property) {
        return properties.get(property);
    }

    @Override
    public final void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public void setActive() {
        this.active = true;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setPropertyValue(String property, String value) {
        this.properties.put(property, value);
    }

    @Override
    public String getPrimaryMessage(String... arguments) {
        return String.format(primaryMessage, arguments);
    }
}
