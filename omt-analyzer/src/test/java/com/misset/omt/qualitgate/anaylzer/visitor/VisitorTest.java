package com.misset.omt.qualitgate.anaylzer.visitor;

import com.misset.omt.qualitygate.analyzer.validators.Validator;
import com.misset.omt.qualitygate.analyzer.visitors.AbstractElementVisitor;
import com.misset.omt.qualitygate.model.OMTElement;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
public abstract class VisitorTest<T extends OMTElement> {

    protected abstract AbstractElementVisitor<T> getVisitor();

    protected <U extends Validator<T>> boolean doVisit(Class<U> validatorClass, T target) {
        AtomicBoolean validationCalled = new AtomicBoolean(false);
        try (MockedConstruction<U> mocked = Mockito.mockConstruction(validatorClass,
                (validator, context) -> doAnswer(invocationOnMock -> {
                    validationCalled.set(true);
                    return null;
                }).when(validator).validate(any()))) {
            getVisitor().visit(target);
            return validationCalled.get();
        }
    }

}
