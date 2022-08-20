package com.misset.omt.qualitygate.model.maps.modelitem;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.GraphSelection;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.maps.actions.Actions;
import com.misset.omt.qualitygate.model.maps.payload.Payload;
import com.misset.omt.qualitygate.model.maps.rules.Rules;
import com.misset.omt.qualitygate.model.scalars.InterpolatedStringElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTCommandsDefinition;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQueriesDefinition;
import com.misset.omt.qualitygate.model.scalars.injected.ODTQuery;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.HandlersSequence;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import com.misset.omt.qualitygate.model.sequences.VariablesSequence;
import com.misset.omt.qualitygate.model.sequences.WatchersSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Activity extends ModelItem {

    public static final String ACTIVITY = "!Activity";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();
    public static final String ON_DEFAULT_CLOSE = "onDefaultClose";

    public static final String ON_START = "onStart";

    public static final String ON_COMMIT = "onCommit";

    public static final String ON_CANCEL = "onCancel";

    public static final String ON_DONE = "onDone";

    public static final String RETURNS = "returns";

    public static final String REASON = "reason";

    static {
        properties.put(TITLE, InterpolatedStringElement::new);
        properties.put(ON_DEFAULT_CLOSE, InterpolatedStringElement::new);
        properties.put(ParamsSequence.PARAMS, ParamsSequence::new);
        properties.put(VariablesSequence.VARIABLES, VariablesSequence::new);
        properties.put(HandlersSequence.HANDLERS, HandlersSequence::new);
        properties.put(GraphSelection.GRAPHS, GraphSelection::new);
        properties.put(WatchersSequence.WATCHERS, WatchersSequence::new);
        properties.put(Rules.RULES, Rules::new);
        properties.put(Prefixes.PREFIXES, Prefixes::new);
        properties.put(ODTQueriesDefinition.QUERIES, ODTQueriesDefinition::new);
        properties.put(ODTCommandsDefinition.COMMANDS, ODTCommandsDefinition::new);
        properties.put(ON_START, ODTScript::new);
        properties.put(ON_COMMIT, ODTScript::new);
        properties.put(ON_CANCEL, ODTScript::new);
        properties.put(ON_DONE, ODTScript::new);
        properties.put(RETURNS, ODTQuery::new);
        properties.put(Actions.ACTIONS, Actions::new);
        properties.put(REASON, StringElement::new);
        properties.put(Payload.PAYLOAD, Payload::new);
    }

    public Activity(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
