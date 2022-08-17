package com.misset.omt.qualitygate.model.maps.modelitem;

import java.util.HashMap;
import java.util.function.Function;

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

public class Component extends ModelItem {

    public static final String COMPONENT = "!Component";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    public static final String ON_INIT = "onInit";

    static {
        properties.put(TITLE, InterpolatedStringElement::new);
        properties.put(Bindings.BINDINGS, Bindings::new);
        properties.put(VariablesSequence.VARIABLES, VariablesSequence::new);
        properties.put(GraphSelection.GRAPHS, GraphSelection::new);
        properties.put(WatchersSequence.WATCHERS, WatchersSequence::new);
        properties.put(Prefixes.PREFIXES, Prefixes::new);
        properties.put(ODTQueriesDefinition.QUERIES, ODTQueriesDefinition::new);
        properties.put(ODTCommandsDefinition.COMMANDS, ODTCommandsDefinition::new);
        properties.put(ON_INIT, ODTScript::new);
        properties.put(Actions.ACTIONS, Actions::new);
        properties.put(Payload.PAYLOAD, Payload::new);
        properties.put(Rules.RULES, Rules::new);
    }

    public Component(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
