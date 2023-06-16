package com.abelhzo.validation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abelhzo.validation.dtos.FilesDTO;
import com.abelhzo.validation.dtos.UsuarioDTO;

import jakarta.validation.Valid;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: UsuarioController.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 15 Junio 2023, 15:02:13.
 * @description: El presente archivo UsuarioController.java fue creado por Abel HZO.
 */
@RestController
@RequestMapping
public class UsuarioController {

	@PostMapping
	public ResponseEntity<UsuarioDTO> validarUsuario(@Valid UsuarioDTO usuarioDTO) {
		return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> validarArchivo(@Valid FilesDTO filesDTO) {
		return new ResponseEntity<String>(filesDTO.getUploadFile().getOriginalFilename(), HttpStatus.OK);
	}
	
}
