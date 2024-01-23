package com.manage.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumericValidator implements ConstraintValidator<NationalCode, Object> {
    @Override
    public void initialize(NationalCode numeric) {
        ConstraintValidator.super.initialize(numeric);
    }

    @Override
    public boolean isValid(Object input, ConstraintValidatorContext context) {
        if (input == null) {
            return false;
        }
        if (input instanceof String) {
            return StringUtil.isNumeric((String) input);

        }
        return input instanceof Number;
    }
}
