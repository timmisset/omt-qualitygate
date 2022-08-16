package com.misset.omt.qualitygate.rules;

import java.util.ArrayList;

import com.misset.omt.qualitygate.checks.OMTChecks;
import com.misset.omt.qualitygate.language.OMTLanguage;
import org.sonar.api.SonarRuntime;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

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

        metadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(OMTChecks.getAllChecksByClass()));

        repository.done();
    }
}
