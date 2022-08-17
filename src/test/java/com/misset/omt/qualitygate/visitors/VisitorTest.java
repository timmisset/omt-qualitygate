package com.misset.omt.qualitygate.visitors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.util.concurrent.atomic.AtomicBoolean;

import com.misset.omt.qualitygate.model.OMTElement;
import com.misset.omt.qualitygate.validators.Validator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sonar.api.batch.rule.ActiveRule;

@ExtendWith(MockitoExtension.class)
public abstract class VisitorTest<T extends OMTElement> {
    @Mock
    protected ActiveRule activeRule;


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
