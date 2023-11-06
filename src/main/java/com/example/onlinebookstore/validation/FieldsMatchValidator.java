package com.example.onlinebookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String field;
    private String repeatedField;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object repeatedFieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(repeatedField);

        if (fieldValue != null) {
            return fieldValue.equals(repeatedFieldValue);
        } else {
            return repeatedFieldValue == null;
        }
    }

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.repeatedField = constraintAnnotation.repeatedField();
    }
}
