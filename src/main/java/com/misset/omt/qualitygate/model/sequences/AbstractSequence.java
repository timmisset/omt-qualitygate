package com.misset.omt.qualitygate.model.sequences;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.SequenceNode;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSequence extends OMTBaseElement {

    List<OMTElement> elements;

    protected AbstractSequence(Node node) {
        super(node);
    }

    @Override
    protected NodeId getExpectedNodeId() {
        return NodeId.sequence;
    }

    @Override
    protected final void parseNode(Node node) {
        elements = new ArrayList<>();
        if(node instanceof SequenceNode) {
            ((SequenceNode) node)
                    .getValue()
                    .stream()
                    .map(this::createInstance)
                    .forEach(elements::add);
        }
    }

    protected abstract OMTElement createInstance(Node node);

    @Override
    public void validateTree(SensorContext context, InputFile inputFile) {
        super.validateTree(context, inputFile);
        elements.forEach(omtElement -> omtElement.validate(context, inputFile));
    }
}
