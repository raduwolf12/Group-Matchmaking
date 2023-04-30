package com.example.demo.validation.validators;

import java.util.Arrays;

import com.example.demo.model.entity.enums.Role;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * The Class ValueOfEnumValidator.
 * 
 * @author Baeldung.com
 */
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, Role> {
	private Role[] subset;

	@Override
	public void initialize(ValueOfEnum constraint) {
		this.subset = constraint.anyOf();
	}

	@Override
	public boolean isValid(Role value, ConstraintValidatorContext context) {
		return value == null || Arrays.asList(subset).contains(value);
	}
}