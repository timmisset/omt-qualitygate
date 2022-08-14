package com.misset.omt.qualitygate.rules;

import com.misset.omt.qualitygate.language.OMTLanguage;
import org.sonar.api.server.rule.RulesDefinition;

public class OMTRulesDefinition implements RulesDefinition {

    private static final String RULES_DEFINITION_FOLDER = "";

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(OMTRepository.REPOSITORY_KEY, OMTLanguage.KEY).setName(OMTRepository.REPOSITORY_NAME);

        repository.done();
    }
}
