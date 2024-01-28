package com.manage.utils.customValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NationalCodeValidator.class)
@Documented
public @interface NationalCode {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
