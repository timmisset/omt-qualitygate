package com.misset.omt.qualitygate.sensor;

import java.util.List;

import com.misset.omt.qualitygate.issue.OMTIssue;
import org.sonar.api.batch.rule.ActiveRules;

/**
 * Intermediate class to decouple the analysis from Sonarqubes core API
 * This will allow a plugin for IDEs to also utilize the ElementVisitors
 */
public interface OMTSensorContext {

    /**
     * Return a list with ActiveRules, these can be fetched from the Sonarqube server or by creating an implementation
     * of every rule in com.misset.omt.qualitygate.rules;
     */
    ActiveRules getActiveRules();

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

}
