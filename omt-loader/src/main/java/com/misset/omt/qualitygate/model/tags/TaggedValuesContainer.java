package com.misset.omt.qualitygate.model.tags;

import com.misset.omt.qualitygate.model.OMTElement;

import java.util.Collection;

public interface TaggedValuesContainer<T extends OMTElement> {

    Collection<String> getExpectedTags();

}
