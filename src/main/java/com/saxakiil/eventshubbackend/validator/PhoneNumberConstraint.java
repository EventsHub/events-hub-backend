package com.saxakiil.eventshubbackend.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberConstraint {
    String message() default "Invalid 'phoneNumber'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}