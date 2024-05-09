package io.ducnt.todo.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<TitleConstraint, String> {
    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext constraintValidatorContext) {
        return nameField != null && nameField.indexOf(" ") > 1;
    }

    @Override
    public void initialize(TitleConstraint constraintAnnotation) {}
}
