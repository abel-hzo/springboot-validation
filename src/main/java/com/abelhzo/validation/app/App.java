package com.abelhzo.validation.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Abel HZO
 * @project: springboot-validation
 * @file: App.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Jueves 15 Junio 2023, 14:55:26.
 * @description: El presente archivo App.java fue creado por Abel HZO.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.abelhzo.validation" })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
