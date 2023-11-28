package com.example.onlinebookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String field;
    private String repeatedField;

    private final BeanWrapperImpl beanWrapper = new BeanWrapperImpl();

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object repeatedFieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(repeatedField);

        return Objects.equals(fieldValue, repeatedFieldValue);
    }

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.repeatedField = constraintAnnotation.repeatedField();
    }
}
