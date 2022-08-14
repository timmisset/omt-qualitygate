package com.misset.omt.qualitygate.model.maps.files;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.maps.Imports;
import com.misset.omt.qualitygate.model.maps.Model;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.scalars.injected.ODTCommandsDefinition;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQueriesDefinition;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class OMTDefaultFile extends AbstractStrictMap implements OMTFile {
    static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        properties.put("import", Imports::new);
        properties.put("model", Model::new);
        properties.put("queries", ODTQueriesDefinition::new);
        properties.put("commands", ODTCommandsDefinition::new);
        properties.put("prefixes", Prefixes::new);
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
