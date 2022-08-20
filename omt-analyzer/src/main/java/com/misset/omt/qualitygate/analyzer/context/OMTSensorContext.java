package com.misset.omt.qualitygate.analyzer.context;

import com.misset.omt.qualitygate.analyzer.issue.OMTIssue;

import java.util.List;

/**
 * Intermediate class to decouple the analysis from Sonarqubes core API
 * This will allow a plugin for IDEs to also utilize the ElementVisitors
 */
public interface OMTSensorContext {

    /**
     * Return a list with ActiveRule keys, these can be fetched from the Sonarqube server or by creating an implementation
     * of every rule in com.misset.omt.qualitygate.rules;
     */
    List<String> getActiveRules();

    /**
     * Return the content of the currently analyzed file to be processed by the OMTParser
     */
    String getContent();

    /**
     * Return the current filename to be used by the OMTParser to determine what kind of file we're dealing with
     */
    String getFilename();

    void newIssue(OMTIssue issue);

    List<OMTIssue> getIssues();

    void setPropertyValue(String ruleKey, String property, String value);

}
