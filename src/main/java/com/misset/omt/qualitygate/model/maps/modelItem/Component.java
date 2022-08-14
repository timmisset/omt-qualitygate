package com.misset.omt.qualitygate.model.maps.modelItem;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.GraphSelection;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.maps.actions.Actions;
import com.misset.omt.qualitygate.model.maps.payload.Payload;
import com.misset.omt.qualitygate.model.maps.rules.Rules;
import com.misset.omt.qualitygate.model.maps.variables.Bindings;
import com.misset.omt.qualitygate.model.scalars.InterpolatedStringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTCommandsDefinition;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQueriesDefinition;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.VariablesSequence;
import com.misset.omt.qualitygate.model.sequences.WatchersSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Component extends ModelItem {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("title", InterpolatedStringElement::new);
        properties.put("bindings", Bindings::new);
        properties.put("variables", VariablesSequence::new);
        properties.put("graphs", GraphSelection::new);
        properties.put("watchers", WatchersSequence::new);
        properties.put("prefixes", Prefixes::new);
        properties.put("queries", ODTQueriesDefinition::new);
        properties.put("commands", ODTCommandsDefinition::new);
        properties.put("onInit", ODTScript::new);
        properties.put("actions", Actions::new);
        properties.put("payload", Payload::new);
        properties.put("rules", Rules::new);
    }

    public Component(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
