package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada principal de la aplicación Spring Boot.
 * 
 * Esta clase configura y arranca el contexto de Spring, inicializando
 * todos los componentes configurados: seguridad, controladores,
 * servicios, repositorios y filtros de la aplicación.
 * 
 * La aplicación implementa un sistema de autenticación JWT que protege
 * los endpoints de la API REST, requiriendo tokens válidos para acceder
 * a los recursos protegidos.
 * 
 * Componentes principales inicializados:
 * - SecurityConfig: Configuración de seguridad con filtros JWT
 * - AuthController: Endpoints de autenticación (/api/auth/**)
 * - ResourceController: Endpoints de recursos protegidos (/api/resources/**)
 * - JwtAuthenticationFilter: Filtro para validación de tokens JWT
 * - JwtTokenUtil: Utilidad para generación y validación de tokens
 */
@SpringBootApplication
public class DemoApplication {
    
    /**
     * Método principal que inicia la aplicación Spring Boot.
     * 
     * @param args argumentos de línea de comandos pasados al proceso
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
}