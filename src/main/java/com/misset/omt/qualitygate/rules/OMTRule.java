package com.misset.omt.qualitygate.rules;

/**
 * An OMTRule describes, using annotations, the key, name and properties of the given Rule.
 * Rules should only describe their purpose and the properties that users can utilize to modify their
 * behavior. The rules should not do any validation themselves. This is done by the Validators.
 * <p>
 * To describe a rule and set things like its default type and severity, add a html and json file to
 * src/main/resources/org/sonar/l10n/omt/rules/omt
 * These will be automatically loaded as long as the name of the files matches exactly with the KEY of annotated rule.
 */
public interface OMTRule {
}
