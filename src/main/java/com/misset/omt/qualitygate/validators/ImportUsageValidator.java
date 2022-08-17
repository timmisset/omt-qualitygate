package com.misset.omt.qualitygate.validators;

import java.util.Arrays;
import java.util.Optional;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.Imports;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.scalars.ImportMemberStringElement;
import com.misset.omt.qualitygate.rules.ImportMustBeUsed;
import com.misset.omt.qualitygate.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.visitors.ScalarVisitorUtil;
import org.apache.commons.lang3.StringUtils;

public class ImportUsageValidator implements Validator<OMTFile> {

    private final AbstractElementVisitor<? extends OMTElement> visitor;

    public ImportUsageValidator(AbstractElementVisitor<? extends OMTElement> visitor) {
        this.visitor = visitor;
    }

    public void validate(OMTFile file) {
        Optional<Imports> imports = TreeUtil.findChild(file, Imports.class);
        if(imports.isEmpty()) {
            return;
        }
        Imports importElement = imports.get();

        String exclusions = visitor.getProperty(ImportMustBeUsed.KEY, ImportMustBeUsed.EXCLUSION);
        if(StringUtils.isNotEmpty(exclusions) &&
                Arrays.asList(exclusions.split(";")).contains(visitor.getInputFile().filename())) {
            return;
        }

        TreeUtil.findChildren(importElement, ImportMemberStringElement.class)
                .stream()
                .filter(element -> !ScalarVisitorUtil.containsValue(file, importElement, element.getValueOrEmpty()))
                .forEach(element -> visitor.newIssue(ImportMustBeUsed.KEY, element));
    }

}
