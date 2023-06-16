package com.abelhzo.validation.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.abelhzo.validation.annotations.ValidFile;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: FilesDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 16 Junio 2023, 15:20:58.
 * @description: El presente archivo FilesDTO.java fue creado por Abel HZO.
 */
public class FilesDTO {

	@ValidFile(message = "Archivo invalido.")
	private MultipartFile uploadFile;

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}
