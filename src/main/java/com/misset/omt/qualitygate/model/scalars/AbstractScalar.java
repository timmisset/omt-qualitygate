package com.misset.omt.qualitygate.model.scalars;

import com.misset.omt.qualitygate.model.OMTBaseElement;
import com.misset.omt.qualitygate.model.OMTElement;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.SequenceNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractScalar extends OMTBaseElement {

    protected AbstractScalar(Node node) {
        super(node);
    }

    @Override
    public NodeId getExpectedNodeId() {
        return NodeId.scalar;
    }

    @Override
    protected void parseNode(Node node) {

    }

    @Override
    public Collection<OMTElement> getChildren() {
        return new ArrayList<>();
    }
}
