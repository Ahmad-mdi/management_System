package com.manage.utils.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumericValidator.class)
@Documented
public @interface NationalCode {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
