package com.abelhzo.validation.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: HandlerExceptions.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 15 Junio 2023, 15:07:54.
 * @description: El presente archivo HandlerExceptions.java fue creado por Abel HZO.
 */
@RestControllerAdvice
public class HandlerExceptions {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidateException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getAllErrors().forEach(err -> {
			errors.put(((FieldError) err).getField(), err.getDefaultMessage());
		});
		return errors;
	}
	
}
