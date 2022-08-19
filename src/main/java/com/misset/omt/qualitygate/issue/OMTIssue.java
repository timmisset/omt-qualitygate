package com.misset.omt.qualitygate.issue;

import org.sonar.api.rule.RuleKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a new issue containing the RuleKey that was violated and the location within the content
 */
public class OMTIssue {

    private final RuleKey key;

    private final OMTIssueLocation location;

    private final List<OMTIssueLocation> additionalLocations = new ArrayList<>();

    public OMTIssue(RuleKey key, OMTIssueLocation location) {
        this.key = key;
        this.location = location;
    }

    public OMTIssueLocation getLocation() {
        return location;
    }

    public RuleKey getKey() {
        return key;
    }

    public void addAdditionalLocation(OMTIssueLocation location) {
        additionalLocations.add(location);
    }

    public List<OMTIssueLocation> getAdditionalLocations() {
        return additionalLocations;
    }
}
