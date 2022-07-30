package com.facebook.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneValidation.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValidator {
    public String message() default "Invalid Mobile Number. Number should be of ten digits and should only contain digits from 0-9";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
