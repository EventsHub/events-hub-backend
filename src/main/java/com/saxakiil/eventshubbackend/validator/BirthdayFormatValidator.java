package com.saxakiil.eventshubbackend.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.saxakiil.eventshubbackend.util.Utils.isNotFutureDate;

public class BirthdayFormatValidator implements ConstraintValidator<BirthdayFormatConstraint, String> {

    @Override
    public void initialize(BirthdayFormatConstraint birthdayFormat) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return contactField != null && isNotFutureDate(contactField);
    }
}