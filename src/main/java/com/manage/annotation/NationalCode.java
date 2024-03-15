package com.manage.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NationalCodeValidator.class)
@Documented
public @interface NationalCode {
    String message() default "invalid.nationalCode";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
