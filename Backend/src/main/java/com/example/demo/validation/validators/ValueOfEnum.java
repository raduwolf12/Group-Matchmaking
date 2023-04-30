package com.example.demo.validation.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.model.entity.enums.Role;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * The Interface ValueOfEnum.
 * 
 * @author Baeldung.com
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    
    /**
     * Any of.
     *
     * @return the role[]
     */
    Role[] anyOf();
    
    /**
     * Message.
     *
     * @return the string
     */
    String message() default "must be any of {anyOf}";
    
    /**
     * Groups.
     *
     * @return the class[]
     */
    Class<?>[] groups() default {};
    
    /**
     * Payload.
     *
     * @return the class<? extends payload>[]
     */
    Class<? extends Payload>[] payload() default {};
}