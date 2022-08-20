package com.misset.omt.qualitgate.anaylzer.visitor;

import com.misset.omt.qualitygate.analyzer.context.OMTSensorContext;
import com.misset.omt.qualitygate.analyzer.rules.Rules;
import com.misset.omt.qualitygate.analyzer.validators.ImportUsageValidator;
import com.misset.omt.qualitygate.analyzer.validators.PrefixUsageValidator;
import com.misset.omt.qualitygate.analyzer.validators.UtilsShouldBeKeptSeparateValidator;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.analyzer.visitors.OMTFileVisitor;
import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OMTFileVisitorTest extends VisitorTest<OMTFile> {

    @Mock
    OMTFile target;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    OMTSensorContext sensorContext;

    @InjectMocks
    OMTFileVisitor fileVisitor;

    @Override
    protected AbstractElementVisitor<OMTFile> getVisitor() {
        return fileVisitor;
    }

    @Test
    void testCallsImportUsageValidatorWhenActive() {
        when(sensorContext.getActiveRules()).thenReturn(Collections.singletonList(
                Rules.Keys.IMPORT_MUST_BE_USED
        ));
        assertTrue(doVisit(ImportUsageValidator.class, target));
    }

    @Test
    void testDoesNotCallImportUsageValidatorWhenNotActive() {
        when(sensorContext.getActiveRules()).thenReturn(Collections.emptyList());
        assertFalse(doVisit(ImportUsageValidator.class, target));
    }

    @Test
    void testCallsUtilsShouldBeKeptSeparateValidatorWhenActive() {
        when(sensorContext.getActiveRules()).thenReturn(Collections.singletonList(
                Rules.Keys.UTILS_SHOULD_BE_KEPT_SEPARATE
        ));
        assertTrue(doVisit(UtilsShouldBeKeptSeparateValidator.class, target));
    }

    @Test
    void testDoesNotCallUtilsShouldBeKeptSeparateValidatorWhenNotActive() {
        when(sensorContext.getActiveRules()).thenReturn(Collections.emptyList());
        assertFalse(doVisit(UtilsShouldBeKeptSeparateValidator.class, target));
    }

    @Test
    void testCallsPrefixUsageValidatorWhenActive() {
        when(sensorContext.getActiveRules()).thenReturn(Collections.singletonList(
                Rules.Keys.PREFIX_MUST_BE_USED
        ));
        assertTrue(doVisit(PrefixUsageValidator.class, target));
    }

    @Test
    void testDoesNotCallPrefixUsageValidatorValidatorWhenNotActive() {
        when(sensorContext.getActiveRules()).thenReturn(Collections.emptyList());
        assertFalse(doVisit(PrefixUsageValidator.class, target));
    }
}
