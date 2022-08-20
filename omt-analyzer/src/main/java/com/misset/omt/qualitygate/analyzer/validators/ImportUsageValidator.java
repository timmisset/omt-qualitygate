package com.misset.omt.qualitygate.analyzer.validators;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.visitors.ElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.ScalarVisitorUtil;
import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.Imports;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.scalars.ImportMemberStringElement;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public class ImportUsageValidator implements Validator<OMTFile> {

    private final ElementVisitor<? extends OMTElement> visitor;

    public ImportUsageValidator(ElementVisitor<? extends OMTElement> visitor) {
        this.visitor = visitor;
    }

    public void validate(OMTFile file) {
        Optional<Imports> imports = TreeUtil.findChild(file, Imports.class);
        if(imports.isEmpty()) {
            return;
        }
        Imports importElement = imports.get();

        String exclusions = visitor.getPropertyValue(Rules.Keys.IMPORT_MUST_BE_USED, Rules.Attributes.IMPORT_MUST_BE_USED_EXCLUSION);
        if(StringUtils.isNotEmpty(exclusions) &&
                Arrays.asList(exclusions.split(";")).contains(visitor.getFilename())) {
            return;
        }

        TreeUtil.findChildren(importElement, ImportMemberStringElement.class)
                .stream()
                .filter(element -> !ScalarVisitorUtil.containsValue(file, importElement, element.getValueOrEmpty()))
                .forEach(element -> visitor.newIssue(Rules.Keys.IMPORT_MUST_BE_USED, element, element.getValueOrEmpty()));
    }

}
