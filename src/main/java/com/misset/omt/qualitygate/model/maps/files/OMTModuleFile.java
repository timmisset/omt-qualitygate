package com.misset.omt.qualitygate.model.maps.files;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.maps.GraphSelection;
import com.misset.omt.qualitygate.model.maps.actions.Actions;
import com.misset.omt.qualitygate.model.maps.actions.GlobalActions;
import com.misset.omt.qualitygate.model.maps.modelitem.GraphShapeHandlers;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.ExportSequence;
import com.misset.omt.qualitygate.model.sequences.ProceduresSequence;
import com.misset.omt.qualitygate.model.sequences.ServicesSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class OMTModuleFile extends AbstractStrictMap implements OMTFile {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    static {
        // include all properties from the root
        properties.putAll(OMTDefaultFile.properties);
        properties.put("moduleName", StringElement::new);
        properties.put("graphs", GraphSelection::new);
        properties.put("onSessionStart", ODTScript::new);
        properties.put("menu", Actions::new);
        properties.put("actions", GlobalActions::new);
        properties.put("services", ServicesSequence::new);
        properties.put("procedures", ProceduresSequence::new);
        properties.put("export", ExportSequence::new);
        properties.put("handlers", GraphShapeHandlers::new);
    }

    public OMTModuleFile(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

    @Override
    public OMTFileType getType() {
        return OMTFileType.MODULE;
    }
}
