package com.misset.omt.qualitygate.model;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.SensorContext;

public interface OMTElement {

    /**
     * Validate the OMT element with the SensorContext against the provided input file
     * Every file on the file system that is part of the OMT language is processed using this method.
     * Within the structure of the OMT file, the entire document is processed and validate is called
     * on every identified element.
     */
    void validate(SensorContext context, InputFile inputFile);

}
