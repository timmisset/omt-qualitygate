package com.misset.omt.qualitygate.validators;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.maps.Model;
import com.misset.omt.qualitygate.model.maps.files.OMTDefaultFile;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.maps.modelitem.*;
import com.misset.omt.qualitygate.model.maps.modelitem.ontology.Ontology;
import com.misset.omt.qualitygate.rules.UtilsShouldBeKeptSeparate;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;

import java.util.Set;
import java.util.stream.Stream;

public class UtilsShouldBeKeptSeparateValidator implements Validator<OMTFile>  {
    private final AbstractElementVisitor<? extends OMTElement> visitor;

    public UtilsShouldBeKeptSeparateValidator(AbstractElementVisitor<? extends OMTElement> visitor) {
        this.visitor = visitor;
    }

    public void validate(OMTFile file) {
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
                        .forEach(omtElement -> visitor.newIssue(UtilsShouldBeKeptSeparate.KEY, omtElement, "Combining utilities with the items that use them is bad, mmmmmkay"));

                // check for model utilities: StandaloneQuery and Procedures
                Set<Class<? extends ModelItem>> utilModelItems = Set.of(StandaloneQuery.class, Procedure.class);
                model.getChildren().stream()
                        .filter(omtElement -> utilModelItems.contains(omtElement.getClass()))
                        .forEach(omtElement -> visitor.newIssue(UtilsShouldBeKeptSeparate.KEY, omtElement, "Combining utilities with the items that use them is bad, mmmmmkay"));
            }
        }
    }

}
