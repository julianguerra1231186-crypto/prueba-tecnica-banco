package com.pruebatecnica.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Clase principal de arranque de la aplicación bancaria.
 * Inicializa el contexto de Spring Boot.
 */

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}

}
