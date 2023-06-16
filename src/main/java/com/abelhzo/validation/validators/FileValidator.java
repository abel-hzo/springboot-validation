package com.abelhzo.validation.validators;

import org.springframework.web.multipart.MultipartFile;

import com.abelhzo.validation.annotations.ValidFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: FileValidator.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 16 Junio 2023, 15:11:44.
 * @description: El presente archivo FileValidator.java fue creado por Abel HZO.
 */
public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		
		if(value == null || value.getOriginalFilename().equals("")) return false;
		
		String contentType = value.getContentType();
		
		if(contentType.equals("image/jpg") || 
		   contentType.equals("image/jpeg") || 
		   contentType.equals("image/png")) {
			return true;
		} else {
			return false;
		}
	
	}

}
