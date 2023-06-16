package com.abelhzo.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abelhzo.validation.validators.TimestampValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: ValidTimestamp.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 16 Junio 2023, 14:14:58.
 * @description: El presente archivo ValidTimestamp.java fue creado por Abel HZO.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimestampValidator.class)
public @interface ValidTimestamp {

	String message() default "";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
