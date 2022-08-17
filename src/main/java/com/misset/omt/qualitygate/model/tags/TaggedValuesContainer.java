package com.misset.omt.qualitygate.model.tags;

import java.util.Collection;

import com.misset.omt.qualitygate.model.OMTElement;

public interface TaggedValuesContainer<T extends OMTElement> {

    Collection<String> getExpectedTags();

}
