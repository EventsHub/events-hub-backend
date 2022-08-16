package com.saxakiil.eventshubbackend.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdayFormatValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthdayFormatConstraint {
    String message() default "Invalid 'birthday'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}