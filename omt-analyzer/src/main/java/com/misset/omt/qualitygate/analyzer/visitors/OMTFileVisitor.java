package com.misset.omt.qualitygate.analyzer.visitors;

import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.validators.ImportUsageValidator;
import com.misset.omt.qualitygate.analyzer.validators.PrefixUsageValidator;
import com.misset.omt.qualitygate.analyzer.validators.UtilsShouldBeKeptSeparateValidator;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;

public class OMTFileVisitor extends AbstractElementVisitor<OMTFile> {

    public static final OMTFileVisitor INSTANCE = new OMTFileVisitor();

    protected OMTFileVisitor() {
        super(OMTFile.class);
    }

    @Override
    public void visit(OMTFile omtFile) {
        validateIfActive(Rules.Keys.IMPORT_MUST_BE_USED, new ImportUsageValidator(this));
        validateIfActive(Rules.Keys.UTILS_SHOULD_BE_KEPT_SEPARATE, new UtilsShouldBeKeptSeparateValidator(this));
        validateIfActive(Rules.Keys.PREFIX_MUST_BE_USED, new PrefixUsageValidator<>(this));
    }

}
