package com.misset.omt.qualitygate.visitors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.misset.omt.qualitygate.model.maps.files.OMTFile;
import com.misset.omt.qualitygate.rules.ImportMustBeUsed;
import com.misset.omt.qualitygate.rules.PrefixMustBeUsed;
import com.misset.omt.qualitygate.rules.UtilsShouldBeKeptSeparate;
import com.misset.omt.qualitygate.sensor.OMTSensorContext;
import com.misset.omt.qualitygate.validators.ImportUsageValidator;
import com.misset.omt.qualitygate.validators.PrefixUsageValidator;
import com.misset.omt.qualitygate.validators.UtilsShouldBeKeptSeparateValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(sensorContext.getActiveRules().find(ImportMustBeUsed.KEY)).thenReturn(activeRule);
        assertTrue(doVisit(ImportUsageValidator.class, target));
    }

    @Test
    void testDoesNotCallImportUsageValidatorWhenNotActive() {
        when(sensorContext.getActiveRules().find(ImportMustBeUsed.KEY)).thenReturn(null);
        assertFalse(doVisit(ImportUsageValidator.class, target));
    }

    @Test
    void testCallsUtilsShouldBeKeptSeparateValidatorWhenActive() {
        when(sensorContext.getActiveRules().find(ImportMustBeUsed.KEY)).thenReturn(null);
        when(sensorContext.getActiveRules().find(UtilsShouldBeKeptSeparate.KEY)).thenReturn(activeRule);
        assertTrue(doVisit(UtilsShouldBeKeptSeparateValidator.class, target));
    }

    @Test
    void testDoesNotCallUtilsShouldBeKeptSeparateValidatorWhenNotActive() {
        when(sensorContext.getActiveRules().find(ImportMustBeUsed.KEY)).thenReturn(null);
        when(sensorContext.getActiveRules().find(UtilsShouldBeKeptSeparate.KEY)).thenReturn(null);
        assertFalse(doVisit(UtilsShouldBeKeptSeparateValidator.class, target));
    }

    @Test
    void testCallsPrefixUsageValidatorWhenActive() {
        when(sensorContext.getActiveRules().find(ImportMustBeUsed.KEY)).thenReturn(null);
        when(sensorContext.getActiveRules().find(UtilsShouldBeKeptSeparate.KEY)).thenReturn(null);
        when(sensorContext.getActiveRules().find(PrefixMustBeUsed.KEY)).thenReturn(activeRule);
        assertTrue(doVisit(PrefixUsageValidator.class, target));
    }

    @Test
    void testDoesNotCallPrefixUsageValidatorValidatorWhenNotActive() {
        when(sensorContext.getActiveRules().find(ImportMustBeUsed.KEY)).thenReturn(null);
        when(sensorContext.getActiveRules().find(UtilsShouldBeKeptSeparate.KEY)).thenReturn(null);
        when(sensorContext.getActiveRules().find(PrefixMustBeUsed.KEY)).thenReturn(null);
        assertFalse(doVisit(PrefixUsageValidator.class, target));
    }
}
