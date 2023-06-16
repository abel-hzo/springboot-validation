package com.abelhzo.validation.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.abelhzo.validation.annotations.ValidTimestamp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: TimestampValidator.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 16 Junio 2023, 14:13:04.
 * @description: El presente archivo TimestampValidator.java fue creado por Abel HZO.
 */
public class TimestampValidator implements ConstraintValidator<ValidTimestamp, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null || value.trim().equals("")) return false;
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf.setLenient(false);
		
		try {
			sdf.parse(value);
		} catch (ParseException e) {
			return false;
		}
		
		return true;
	}

}
