package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.checks.ImportMustBeUsed;
import com.misset.omt.qualitygate.model.TreeUtil;
import com.misset.omt.qualitygate.model.maps.Imports;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.model.scalars.ImportMemberStringElement;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.Optional;

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
                .filter(element -> !ScalarVisitor.isUsed(file, imports.get(), element.getValueOrEmpty()))
                .forEach(element -> newIssue(ImportMustBeUsed.KEY, element));
    }
}
