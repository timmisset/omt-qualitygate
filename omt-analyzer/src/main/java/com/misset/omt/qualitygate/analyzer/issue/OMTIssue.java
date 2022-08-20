package com.misset.omt.qualitygate.analyzer.issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a new com.misset.omt.qualitygate.analyzer.issue containing the RuleKey that was violated and the location within the content
 */
public class OMTIssue {

    private final String key;

    private final OMTIssueLocation location;

    private final List<OMTIssueLocation> additionalLocations = new ArrayList<>();

    public OMTIssue(String key, OMTIssueLocation location) {
        this.key = key;
        this.location = location;
    }

    public OMTIssueLocation getLocation() {
        return location;
    }

    public String getKey() {
        return key;
    }

    public void addAdditionalLocation(OMTIssueLocation location) {
        additionalLocations.add(location);
    }

    public List<OMTIssueLocation> getAdditionalLocations() {
        return additionalLocations;
    }
}
