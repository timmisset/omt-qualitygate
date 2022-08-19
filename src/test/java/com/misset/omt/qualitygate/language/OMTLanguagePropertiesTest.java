package com.misset.omt.qualitygate.language;

import org.junit.jupiter.api.Test;
import org.sonar.api.config.PropertyDefinition;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OMTLanguagePropertiesTest {

    @Test
    void testGetProperties() {
        List<PropertyDefinition> properties = OMTLanguageProperties.getProperties();
        assertEquals(1, properties.size());

        PropertyDefinition propertyDefinition = properties.get(0);
        assertEquals(OMTLanguageProperties.FILE_SUFFIXES_KEY, propertyDefinition.key());
        assertEquals(OMTLanguageProperties.FILE_SUFFIXES_DEFAULT_VALUE, propertyDefinition.defaultValue());
    }

}
