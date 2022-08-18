package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.sensor.OMTSensorContext;
import org.sonar.api.rule.RuleKey;

/**
 * Element visitors are used to trigger on certain element types in the OMT structure
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

    String getProperty(RuleKey key, String propertyKey);

    void newIssue(RuleKey key, OMTElement element);

    String getFilename();
}
