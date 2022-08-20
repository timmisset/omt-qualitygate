package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.analyzer.context.OMTSensorContext;
import com.misset.omt.qualitygate.model.OMTElement;
import org.yaml.snakeyaml.nodes.Node;

/**
 * Element com.misset.omt.qualitygate.analyzer.visitors are used to trigger on certain element types in the OMT structure
 * Elements should be coupled 1:1 to the OMTElement they implement. This means that a single
 * visitor is responsible to do all Checks required on an element.
 * <p>
 * For example, the ModelItemVisitor is responsible for checking if all variables are used and
 * if all prefixes are used.
 * <p>
 * Unless the Check is very small and specific to the single visit, use Validators to do perform the Checks.
 */
public interface ElementVisitor<T extends OMTElement> {

    void visitElements(OMTSensorContext context);

    void visit(T element);

    String getPropertyValue(String key, String propertyKey);

    void newIssue(String key, OMTElement element, String... messageArguments);

    void newIssue(String key, Node node, String... messageArguments);

    String getFilename();
}
