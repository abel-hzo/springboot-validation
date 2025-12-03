## Aplicación demo de validación de campos en SpringBoot

Esta aplicación nos mostrará la forma en la que podemos validar los campos de un objeto DTO que capturará la información proveniente
del frontend y también cómo podemos realizar validaciones personalizadas.

Primero, lo que necesitaremos será agregar la siguiente dependencia en nuestro pom.xml.

> pom.xml

``` xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

En nuestro objeto DTO colocaremos las anotaciones para cada campo que indicarán de qué forma tiene que ser validado.

> UsuarioDTO.java

``` java
public class UsuarioDTO {

	@NumberFormat(style = Style.NUMBER)
	private Long idUsuario;
	@NotBlank(message = "Llene campo usuario.")
	@Pattern(regexp = "^[A-Za-z]*$", message = "Usuario solo agregue texto sin espacios.")
	@Size(min = 4, max = 15, message = "Usuario mas de 4 caracteres y menos de 15.")
	private String username;
	@NotBlank(message = "Llene campo contraseña.")
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

	// ......
	// Getters y Setters
	// ......
}
```

Como podemos notar, el campo registro tiene una validación personalizada que se definió en la anotación @ValidTimestamp(message = "Fecha de Registro inválido."),
el cual se creará de la siguiente manera:

Primero implementamos la lógica de validación, donde definimos qué requisitos debe cumplir el campo para que sea válido.

> TimestampValidator.java

``` java
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
```

Después creamos la anotación donde en la anotación @Constraint colocaremos la clase que implementa la lógica de validación.

> ValidTimestamp.java

``` java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimestampValidator.class)
public @interface ValidTimestamp {

	String message() default "";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
}
```

Ya solo indicamos que nos valide todos los campos del objeto con la anotación @Valid en el parámetro del método del servicio REST.

> UsuarioController.java

``` java
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
```
Y lo mismo podemos hacer para validar la subida de archivos Multipart, ya sea su tamaño, extensión u otra característica.

> FilesDTO.java

``` java
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
```

> FileValidator.java

``` java
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
```

> ValidFile.java

``` java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class )
public @interface ValidFile {
	
	String message() default "";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
```

### Pruebas de validación

Una vez levantada nuestra aplicación, podemos realizar las pruebas necesarias ejecutando los siguientes comandos.

#### Test con datos erroneos:

``` bash
curl -X POST -F "idUsuario=1000" -F "username=abel hzo" -F "contrase=abcd" -F "email=esto no es un email" -F "nacimiento=2025-12-15" -F "codPostal=114245" -F "registro=1998-12-24" http://127.0.0.1:8080/
```

``` json
{
  "contrase": "Password mas de 6 caracteres y menos de 20",
  "codPostal": "Codigo Postal debe tener 5 digitos",
  "email": "Formato de Email invalido.",
  "nacimiento": "Debe ser una fecha pasada",
  "username": "Usuario solo agregue texto sin espacios.",
  "registro": "Fecha de Registro invalido."
}
```

``` bash
curl -X POST -F "idUsuario=1000" -F "email=esto no es un email" -F "codPostal=5588" -F "registro=1998-12-28" http://127.0.0.1:8080/
```

``` json
{
  "contrase": "Llene campo contraseña.",
  "codPostal": "Codigo Postal debe tener 5 digitos",
  "email": "Formato de Email invalido.",
  "nacimiento": "Llene campo fecha de nacimiento.",
  "registro": "Fecha de Registro invalido.",
  "username": "Llene campo usuario."
}
```

``` bash
curl -X PUT -F "uploadFile=@/home/user/Pictures/archivo.txt" http://127.0.0.1:8080/
```

``` json
{
  "uploadFile": "Archivo invalido."
}
```

#### Test con datos correctos:

``` bash
curl -X POST -F "idUsuario=1000" -F "username=abelhzo" -F "contrase=admin123" -F "email=abel@mail.com" -F "nacimiento=1995-12-15" -F "codPostal=11445" -F "registro=1998-12-24 12:00:00.000" http://127.0.0.1:8080/
```

``` json
{
  "idUsuario": 1000,
  "username": "abelhzo",
  "contrase": "admin123",
  "email": "abel@mail.com",
  "nacimiento": "1995-12-15T00:00:00.000+00:00",
  "codPostal": 11445,
  "registro": "1998-12-24 12:00:00.000"
}
```

``` bash
curl -X PUT -F "uploadFile=@/home/user/Pictures/artemis-captures/artemis1.png" http://127.0.0.1:8080/
```

```
artemis1.png
```

