package com.saxakiil.eventshubbackend.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;

public class StartDateValidator implements ConstraintValidator<StartDateConstraint, Long> {

    @Override
    public void initialize(StartDateConstraint startDateConstraint) {
    }

    @Override
    public boolean isValid(Long contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField > Instant.now().toEpochMilli();
    }
}
