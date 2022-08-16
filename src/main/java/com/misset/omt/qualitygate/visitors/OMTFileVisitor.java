package com.misset.omt.qualitygate.visitors;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.misset.omt.qualitygate.checks.ImportMustBeUsed;
import com.misset.omt.qualitygate.checks.UtilsShouldBeKeptSeparate;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.Imports;
import com.misset.omt.qualitygate.model.maps.Model;
import com.misset.omt.qualitygate.model.maps.files.OMTDefaultFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.modelitem.Activity;
import com.misset.omt.qualitygate.model.maps.modelitem.Component;
import com.misset.omt.qualitygate.model.maps.modelitem.Loadable;
import com.misset.omt.qualitygate.model.maps.modelitem.ModelItem;
import com.misset.omt.qualitygate.model.maps.modelitem.Procedure;
import com.misset.omt.qualitygate.model.maps.modelitem.StandaloneQuery;
import com.misset.omt.qualitygate.model.maps.modelitem.ontology.Ontology;
import com.misset.omt.qualitygate.model.scalars.ImportMemberStringElement;
import org.apache.commons.lang3.StringUtils;

public class OMTFileVisitor extends ElementVisitor<OMTFile> {

    public static final OMTFileVisitor INSTANCE = new OMTFileVisitor();

    @Override
    protected Class<OMTFile> getOMTElementClass() {
        return OMTFile.class;
    }

    @Override
    void visit(OMTFile omtFile) {
        if(isActive(ImportMustBeUsed.KEY)) {
            validateImportUsages(omtFile);
        }

        if(isActive(UtilsShouldBeKeptSeparate.KEY)) {
            validateUtilsShouldBeKeptSeparate(omtFile);
        }
    }

    private void validateImportUsages(OMTFile file) {
        Optional<Imports> imports = TreeUtil.findChild(file, Imports.class);
        if(imports.isEmpty()) {
            return;
        }

        String exclusions = getProperty(ImportMustBeUsed.KEY, ImportMustBeUsed.EXCLUSION);
        if(StringUtils.isNotEmpty(exclusions) &&
                Arrays.asList(exclusions.split(";")).contains(inputFile.filename())) {
            return;
        }

        TreeUtil.findChildren(file, ImportMemberStringElement.class)
                .stream()
                .filter(element -> !ScalarVisitorUtil.isUsed(file, imports.get(), element.getValueOrEmpty()))
                .forEach(element -> newIssue(ImportMustBeUsed.KEY, element));
    }

    private void validateUtilsShouldBeKeptSeparate(OMTFile file) {
        if(file instanceof OMTDefaultFile) {
            OMTDefaultFile defaultFile = (OMTDefaultFile) file;
            Model model = defaultFile.get(OMTDefaultFile.MODEL, Model.class);
            if(model == null) { return; }
            boolean hasModelItems = model.getChildren().stream().anyMatch(
                    omtElement -> omtElement instanceof Activity ||
                            omtElement instanceof Component ||
                            omtElement instanceof Ontology ||
                            omtElement instanceof Loadable
            );

            if(hasModelItems) {
                // check for root utilities
                Stream.of(OMTDefaultFile.COMMANDS, OMTDefaultFile.QUERIES)
                        .filter(defaultFile::has)
                        .map(key -> defaultFile.get(key, OMTElement.class))
                        .forEach(omtElement -> newIssue(UtilsShouldBeKeptSeparate.KEY, omtElement));

                // check for model utilities: StandaloneQuery and Procedures
                Set<Class<? extends ModelItem>> utilModelItems = Set.of(StandaloneQuery.class, Procedure.class);
                model.getChildren().stream()
                        .filter(omtElement -> utilModelItems.contains(omtElement.getClass()))
                        .forEach(omtElement -> newIssue(UtilsShouldBeKeptSeparate.KEY, omtElement));
            }
        }
    }
}
