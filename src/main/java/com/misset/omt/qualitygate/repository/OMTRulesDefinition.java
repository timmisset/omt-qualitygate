package com.misset.omt.qualitygate.repository;

import com.misset.omt.qualitygate.language.OMTLanguage;
import com.misset.omt.qualitygate.rules.OMTRules;
import org.sonar.api.SonarRuntime;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

import java.util.ArrayList;

/**
 * The OMTRulesDefinition will create a new Repository that contains all the Rules available to the OMT Language
 */
public class OMTRulesDefinition implements RulesDefinition {
    private static final Logger LOGGER = Loggers.get(OMTRulesDefinition.class);

    /**
     * Path to the directory/folder containing the descriptor files (JSON and HTML) for the rules
     */
    public static final String RULES_DEFINITION_FOLDER = "org/sonar/l10n/omt/rules/omt";
    private final SonarRuntime sonarRuntime;

    public OMTRulesDefinition(SonarRuntime sonarRuntime) {
        this.sonarRuntime = sonarRuntime;
    }

    @Override
    public void define(Context context) {
        LOGGER.debug("creating OMT repository");
        NewRepository repository = context.createRepository(OMTRepository.REPOSITORY_KEY, OMTLanguage.KEY).setName(OMTRepository.REPOSITORY_NAME);

        RuleMetadataLoader metadataLoader = new RuleMetadataLoader(RULES_DEFINITION_FOLDER, sonarRuntime);

        metadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(OMTRules.getAllChecksByClass()));

        repository.done();
    }
}
