package com.misset.omt.qualitygate.model.maps.files;

import java.util.HashMap;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.maps.Imports;
import com.misset.omt.qualitygate.model.maps.Model;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.scalars.injected.ODTCommandsDefinition;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQueriesDefinition;
import org.yaml.snakeyaml.nodes.Node;

public class OMTDefaultFile extends AbstractStrictMap implements OMTFile {

    public static final String IMPORT = "import";
    public static final String MODEL = "model";
    public static final String QUERIES = "queries";
    public static final String COMMANDS = "commands";
    public static final String PREFIXES = "prefixes";

    static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put(IMPORT, Imports::new);
        properties.put(MODEL, Model::new);
        properties.put(QUERIES, ODTQueriesDefinition::new);
        properties.put(COMMANDS, ODTCommandsDefinition::new);
        properties.put(PREFIXES, Prefixes::new);
    }

    public OMTDefaultFile(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

    @Override
    public OMTFileType getType() {
        return OMTFileType.DEFAULT;
    }
}
