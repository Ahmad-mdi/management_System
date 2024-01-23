package com.manage.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NationalCodeValidator.class)
@Documented
public @interface NationalCodeValid {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
