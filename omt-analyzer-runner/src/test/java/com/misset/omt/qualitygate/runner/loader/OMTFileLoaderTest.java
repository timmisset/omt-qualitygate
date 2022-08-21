package com.misset.omt.qualitygate.runner.loader;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OMTFileLoaderTest {

    @Test
    void getOMTFiles() {
        List<File> omtFiles = OMTFileLoader.getOMTFiles();
        assertFalse(omtFiles.isEmpty());
        assertTrue(omtFiles.stream().map(File::getName).collect(Collectors.toList()).containsAll(
                List.of("file.omt", "subfolder-file.omt")
        ));
    }
}
