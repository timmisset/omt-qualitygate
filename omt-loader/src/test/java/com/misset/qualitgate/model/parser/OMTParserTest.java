package com.misset.qualitgate.model.parser;

import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.model.parser.OMTParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OMTParserTest {

    @Test
    void testParsesOmtFile() {
        Path path = Paths.get("src/test/resources/simple-omt-file.omt");
        OMTFile omtFile = new OMTParser().process(path.toFile());
        assertNotNull(omtFile);
        assertEquals(OMTFileType.DEFAULT, omtFile.getType());
    }

}
