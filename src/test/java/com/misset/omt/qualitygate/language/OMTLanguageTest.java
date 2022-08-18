package com.misset.omt.qualitygate.language;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OMTLanguageTest {

    OMTLanguage language = new OMTLanguage();

    @Test
    void getKey() {
        assertEquals("omt", language.getKey());
    }

    @Test
    void getName() {
        assertEquals("OMT", language.getName());
    }

    @Test
    void getFileSuffixes() {
        assertArrayEquals(new String[] { "omt" }, language.getFileSuffixes());
    }

    @Test
    void publishAllFiles() {
        assertTrue(language.publishAllFiles());
    }
}
