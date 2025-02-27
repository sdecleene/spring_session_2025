package com.sdcconsulting.sessions.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {

    @Override
    public void initialize(ValidDateOfBirth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(
            final LocalDate localDate,
            final ConstraintValidatorContext constraintValidatorContext
    ) {
        return LocalDate.now().getYear() - localDate.getYear() >= 18;
    }

}
