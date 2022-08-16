package com.misset.omt.qualitygate.model.maps.files.interfacefile;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFileType;
import org.yaml.snakeyaml.nodes.Node;

public class OMTInterfaceFile extends AbstractStrictMap implements OMTFile {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("prefixes", Prefixes::new);
        properties.put("declare", DeclareModule::new);
    }

    public OMTInterfaceFile(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

    @Override
    public OMTFileType getType() {
        return OMTFileType.INTERFACE;
    }
}
