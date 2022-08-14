package com.misset.omt.qualitygate.model.maps.modelItem;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.GraphSelection;
import com.misset.omt.qualitygate.model.maps.Prefixes;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.scalars.injected.ODTScript;
import com.misset.omt.qualitygate.model.sequences.HandlersSequence;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import com.misset.omt.qualitygate.model.sequences.VariablesSequence;
import org.yaml.snakeyaml.nodes.Node;

import java.util.HashMap;
import java.util.function.Function;

public class Procedure extends ModelItem {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    static {
        properties.put("params", ParamsSequence::new);
        properties.put("variables", VariablesSequence::new);
        properties.put("graphs", GraphSelection::new);
        properties.put("prefixes", Prefixes::new);
        properties.put("onRun", ODTScript::new);
        properties.put("handlers", HandlersSequence::new);
        properties.put("reason", StringElement::new);
    }

    public Procedure(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
