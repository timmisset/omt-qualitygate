package com.misset.omt.qualitygate.visitors;

import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.rules.ImportMustBeUsed;
import com.misset.omt.qualitygate.rules.PrefixMustBeUsed;
import com.misset.omt.qualitygate.rules.UtilsShouldBeKeptSeparate;
import com.misset.omt.qualitygate.validators.ImportUsageValidator;
import com.misset.omt.qualitygate.validators.PrefixUsageValidator;
import com.misset.omt.qualitygate.validators.UtilsShouldBeKeptSeparateValidator;

public class OMTFileVisitor extends AbstractElementVisitor<OMTFile> {

    public static final OMTFileVisitor INSTANCE = new OMTFileVisitor();

    protected OMTFileVisitor() {
        super(OMTFile.class);
    }

    @Override
    void visit(OMTFile omtFile) {
        validateIfActive(ImportMustBeUsed.KEY, new ImportUsageValidator(this));
        validateIfActive(UtilsShouldBeKeptSeparate.KEY, new UtilsShouldBeKeptSeparateValidator(this));
        validateIfActive(PrefixMustBeUsed.KEY, new PrefixUsageValidator<>(this));
    }

}
