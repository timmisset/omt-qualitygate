package com.misset.omt.qualitygate.validators;

import com.misset.omt.qualitygate.model.OMTElement;

/**
 * Validators are used to decouple the Visitors from the logic to validate a certain Check given the OMTElement as context.
 * Use validators when the same check should be performed on different levels. For example, Prefix usage should be performed
 * on the OMTFile and ModelItems.
 */
public interface Validator<T extends OMTElement> {
    void validate(T element);

}
