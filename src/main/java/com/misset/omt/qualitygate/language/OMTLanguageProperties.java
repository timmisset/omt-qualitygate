package com.misset.omt.qualitygate.language;

import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import java.util.Collections;
import java.util.List;

public class OMTLanguageProperties {

  public static final String FILE_SUFFIXES_KEY = "sonar.omt.file.suffixes";
  public static final String FILE_SUFFIXES_DEFAULT_VALUE = ".omt";

  private OMTLanguageProperties() {
    // private class
  }

  public static List<PropertyDefinition> getProperties() {
    return Collections.singletonList(PropertyDefinition.builder(FILE_SUFFIXES_KEY)
            .multiValues(true)
            .defaultValue(FILE_SUFFIXES_DEFAULT_VALUE)
            .category(OMTLanguage.NAME)
            .name("File Suffixes")
            .description("List of suffixes for files to analyze.")
            .onQualifiers(Qualifiers.PROJECT)
            .build());
  }

}
