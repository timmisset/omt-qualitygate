package com.misset.omt.qualitygate.model.maps.files.interfacefile;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.AbstractStrictMap;
import com.misset.omt.qualitygate.model.scalars.FixedValuesStringElement;
import com.misset.omt.qualitygate.model.scalars.StringElement;
import com.misset.omt.qualitygate.model.sequences.ParamsSequence;
import org.yaml.snakeyaml.nodes.Node;

public class DeclareInterface extends AbstractStrictMap {

    private static final HashMap<String, Function<Node, OMTElement>> properties = new HashMap<>();

    private static final String TYPE = "type";

    private static final String RETURNS = "returns";

    private static final List<String> VALUES = List.of("Activity", "Procedure", "Command", "Query");

    static {
        properties.put(TYPE, node -> new FixedValuesStringElement(node, VALUES));
        properties.put(ParamsSequence.PARAMS, ParamsSequence::new);
        properties.put(RETURNS, StringElement::new);
    }

    public DeclareInterface(Node node) {
        super(node);
    }

    @Override
    protected HashMap<String, Function<Node, OMTElement>> getProperties() {
        return properties;
    }

}
