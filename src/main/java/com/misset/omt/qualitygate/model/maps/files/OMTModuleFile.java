package com.misset.omt.qualitygate.model.maps.files;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.maps.GraphSelection;
import com.misset.omt.qualitygate.model.maps.actions.Actions;
import com.misset.omt.qualitygate.model.maps.actions.GlobalActions;
import com.misset.omt.qualitygate.model.maps.modelitem.GraphShapeHandlers;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.ExportSequence;
import com.misset.omt.qualitygate.model.sequences.HandlersSequence;
import com.misset.omt.qualitygate.model.sequences.ProceduresSequence;
import com.misset.omt.qualitygate.model.sequences.ServicesSequence;
import org.yaml.snakeyaml.nodes.Node;

public class OMTModuleFile extends AbstractStrictMap implements OMTFile {
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    private static final String MODULE_NAME = "moduleName";
    private static final List<String> REQUIRED = List.of(MODULE_NAME);
    private static final String ON_SESSION_START = "onSessionStart";

    static {
        // include all properties from the root
        properties.putAll(OMTDefaultFile.properties);
        properties.put(MODULE_NAME, StringElement::new);
        properties.put(GraphSelection.GRAPHS, GraphSelection::new);
        properties.put(ON_SESSION_START, ODTScript::new);
        properties.put(Actions.MENU, Actions::new);
        properties.put(Actions.ACTIONS, GlobalActions::new);
        properties.put(ServicesSequence.SERVICES, ServicesSequence::new);
        properties.put(ProceduresSequence.PROCEDURES, ProceduresSequence::new);
        properties.put(ExportSequence.EXPORT, ExportSequence::new);
        properties.put(HandlersSequence.HANDLERS, GraphShapeHandlers::new);
    }

    public OMTModuleFile(Node node) {
        super(node);
    }

    @Override
    protected Collection<String> getRequiredKeys() {
        return REQUIRED;
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
