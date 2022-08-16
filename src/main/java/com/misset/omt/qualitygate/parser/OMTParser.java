package com.misset.omt.qualitygate.parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import com.misset.omt.qualitygate.model.maps.files.OMTDefaultFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import com.misset.omt.qualitygate.model.maps.files.OMTModuleFile;
import com.misset.omt.qualitygate.model.maps.files.interfacefile.OMTInterfaceFile;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Node;

public class OMTParser {

    private final Yaml yaml;

    public OMTParser() {
        yaml = new Yaml();
    }

    public OMTFile process(File file) {
        try(FileReader reader = new FileReader(file)){
            return parseFile(reader, getFileType(file.getName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public OMTFileType getFileType(String name) {
        OMTFileType fileType;
        if(name.equals("module.omt") || name.endsWith(".module.omt")) {
            fileType = OMTFileType.MODULE;
        } else if(name.equals("interface.omt") || name.endsWith(".interface.omt")) {
            fileType = OMTFileType.INTERFACE;
        } else {
            fileType = OMTFileType.DEFAULT;
        }
        return fileType;
    }
    public OMTFile process(String content, OMTFileType omtFileType) {
        return parseFile(new StringReader(content), omtFileType);
    }
    private OMTFile parseFile(Reader reader, OMTFileType omtFileType) {
        Node node = yaml.compose(reader);
        if(omtFileType == OMTFileType.DEFAULT) {
            return new OMTDefaultFile(node);
        } else if(omtFileType == OMTFileType.INTERFACE) {
            return new OMTInterfaceFile(node);
        } else if(omtFileType == OMTFileType.MODULE) {
            return new OMTModuleFile(node);
        }
        return null;
    }

}
