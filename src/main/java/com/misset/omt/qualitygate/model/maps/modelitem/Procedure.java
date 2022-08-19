package com.misset.omt.qualitygate.model.maps.modelitem;

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

    public static final String PROCEDURE = "!Procedure";
    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    public static final String ON_RUN = "onRun";

    public static final String REASON = "reason";

    static {
        properties.put(ParamsSequence.PARAMS, ParamsSequence::new);
        properties.put(VariablesSequence.VARIABLES, VariablesSequence::new);
        properties.put(GraphSelection.GRAPHS, GraphSelection::new);
        properties.put(Prefixes.PREFIXES, Prefixes::new);
        properties.put(ON_RUN, ODTScript::new);
        properties.put(HandlersSequence.HANDLERS, HandlersSequence::new);
        properties.put(REASON, StringElement::new);
    }

    public Procedure(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }
}
