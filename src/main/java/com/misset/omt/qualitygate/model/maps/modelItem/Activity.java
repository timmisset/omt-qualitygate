package com.misset.omt.qualitygate.model.maps.modelItem;

import com.misset.omt.qualitygate.model.OMTBaseElement;
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

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("title", InterpolatedStringElement::new);
        properties.put("onDefaultClose", InterpolatedStringElement::new);
        properties.put("params", ParamsSequence::new);
        properties.put("variables", VariablesSequence::new);
        properties.put("handlers", HandlersSequence::new);
        properties.put("graphs", GraphSelection::new);
        properties.put("watchers", WatchersSequence::new);
        properties.put("rules", Rules::new);
        properties.put("prefixes", Prefixes::new);
        properties.put("queries", ODTQueriesDefinition::new);
        properties.put("commands", ODTCommandsDefinition::new);
        properties.put("onStart", ODTScript::new);
        properties.put("onCommit", ODTScript::new);
        properties.put("onCancel", ODTScript::new);
        properties.put("onDone", ODTScript::new);
        properties.put("returns", ODTQuery::new);
        properties.put("actions", Actions::new);
        properties.put("reason", StringElement::new);
        properties.put("payload", Payload::new);
    }

    public Activity(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
