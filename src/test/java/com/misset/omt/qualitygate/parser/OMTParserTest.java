package com.misset.omt.qualitygate.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import org.junit.jupiter.api.Test;

class OMTParserTest {

    @Test
    void testParsesOmtFile() {
        Path path = Paths.get("src/test/resources/OMTParser/simple-omt-file.omt");
        OMTFile omtFile = new OMTParser().process(path.toFile());
        assertNotNull(omtFile);
        assertEquals(OMTFileType.DEFAULT, omtFile.getType());
    }

}
