package com.abelhzo.validation.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.abelhzo.validation.annotations.ValidTimestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: UsuarioDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 15 Junio 2023, 14:57:41.
 * @description: El presente archivo UsuarioDTO.java fue creado por Abel HZO.
 */
public class UsuarioDTO {

	@NumberFormat(style = Style.NUMBER)
	private Long idUsuario;
	@NotBlank(message = "Llene campo usuario.")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Usuario solo agregue texto sin espacios.")
	@Size(min = 4, max = 15, message = "Usuario mas de 4 caracteres y menos de 15.")
	private String username;
	@NotBlank(message = "Llene campo comtraseña.")
	@Size(min = 6, max = 20, message = "Password mas de 6 caracteres y menos de 20")
	private String contrase;
	@Email(message = "Formato de Email invalido.")
	private String email;
	@Past(message = "Debe ser una fecha pasada")
	@NotNull(message = "Llene campo fecha de nacimiento.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nacimiento;              // Este campo si se envia vacio por default valida.
	@NumberFormat(style = Style.NUMBER)
	@Min(value = 10000, message = "Codigo Postal debe tener 5 digitos")
	@Max(value = 99999, message = "Codigo Postal debe tener 5 digitos")
	private Integer codPostal;
	@ValidTimestamp(message = "Fecha de Registro invalido.")
	private String registro;               // Este campo en vez de utilizar Timestamp se utiliza String

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContrase() {
		return contrase;
	}

	public void setContrase(String contrase) {
		this.contrase = contrase;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Integer getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(Integer codPostal) {
		this.codPostal = codPostal;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

}
