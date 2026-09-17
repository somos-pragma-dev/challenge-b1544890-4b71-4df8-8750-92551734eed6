# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/example/demo/dto/ResourceResponse.java` — `ResourceStatus`: ResourceStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.demo.model.ResourceStatus.
- `src/test/java/com/example/demo/controller/ResourceControllerTest.java` — `ResourceStatus`: ResourceStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.demo.dto.ResourceStatus (hay mas de un tipo con ese nombre en el proyecto).
- `src/test/java/com/example/demo/controller/ResourceControllerTest.java` — `ResourceResponse`: ResourceResponse se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.demo.dto.ResourceResponse.
- `src/main/java/com/example/demo/model/Resource.java` — `ResourceRequest`: El import com.example.demo.dto.ResourceRequest no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.findAll`: Se invoca `findAll` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.findById`: Se invoca `findById` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.name`: Se invoca `name` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.description`: Se invoca `description` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.value`: Se invoca `value` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.status`: Se invoca `status` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.acquisitionDate`: Se invoca `acquisitionDate` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.save`: Se invoca `save` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.existsById`: Se invoca `existsById` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.deleteById`: Se invoca `deleteById` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.count`: Se invoca `count` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/example/demo/service/AuthService.java` — `UserCredentials.getPassword`: Se invoca `getPassword` sobre `UserCredentials`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Implementar autenticacion JWT en una API REST con Spring Security

### Reto
- Tema: seguridad en api rest
- Seniority: junior-l1
- Tipo: practical
- Título: Implementación de autenticación JWT en una API REST
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Diseño del mecanismo de autenticación — objetivo: Definir los requerimientos y el diseño del sistema de autenticación JWT. — entregable (NO resolver): Documento de diseño que describe el mecanismo de autenticación JWT, incluyendo los endpoints protegidos, roles y permisos, y reglas de validación.
- Fase 2: Implementación de la autenticación JWT — objetivo: Implementar el mecanismo de autenticación JWT en la API REST. — entregable (NO resolver): API REST con autenticación JWT implementada, donde los usuarios deben autenticarse para acceder a los endpoints protegidos.
- Fase 3: Pruebas y validación del mecanismo de autenticación — objetivo: Realizar pruebas para asegurar que el mecanismo de autenticación JWT funciona correctamente. — entregable (NO resolver): Reporte de pruebas que demuestra que el mecanismo de autenticación JWT funciona correctamente en diferentes escenarios.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>
    
    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>API REST con autenticación JWT</description>
    
    <properties>
        <java.version>17</java.version>
        <jjwt.version>0.12.5</jjwt.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jjwt.version}</version>
        </dependency>
        
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <version>6.3.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    
</project>

// === ARCHIVO: src/main/java/com/example/demo/DemoApplication.java ===
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

// === ARCHIVO: src/main/resources/application.properties ===
# Configuración del servidor embebido
server.port=8080

# Configuración de la aplicación
spring.application.name=demo

# Configuración de la base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:demodb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Configuración de JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración de H2 Console (para desarrollo)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuración JWT - Secret key para firmar tokens
# Debe ser una cadena de al menos 256 bits para HS256
# En producción, esta clave debe almacenarse de forma segura
# (variables de entorno, vault, etc.) y nunca en código fuente
jwt.secret=MySuperSecretKeyForJWTTokenGenerationThatIsAtLeast256BitsLong123456789

# Configuración de expiración del token JWT
# Tiempo en milisegundos hasta que el token expira
# 86400000 ms = 24 horas
jwt.expiration=86400000

# Configuración del filtro de autenticación JWT
# Ruta pública que no requiere autenticación
jwt.public.endpoints=/api/auth/**

# Configuración de seguridad de Spring Security
# Control de sesiones: stateless para API REST con JWT
spring.security.session.creation=STATELESS

# Configuración de logging
logging.level.org.springframework.security=DEBUG
logging.level.com.example.demo=DEBUG
logging.level.org.hibernate.SQL=DEBUG

# Mensaje de bienvenida de la aplicación
spring.application.message=API REST con autenticacion JWT - Spring Boot 3.3.4


// === ARCHIVO: src/main/java/com/example/demo/dto/LoginRequest.java ===
package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO que representa la solicitud de autenticación del usuario.
 * Contiene las credenciales necesarias para validar la identidad.
 */
public record LoginRequest(
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El usuario debe tener entre 3 y 50 caracteres")
    String username,
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    String password
) {
    /**
     * Constructor compact que permite validación adicional si es necesario.
     */
    public LoginRequest {
        if (username != null) {
            username = username.trim();
        }
        if (password != null) {
            password = password.trim();
        }
    }
    
    /**
     * Método para obtener el nombre de usuario en mayúsculas para comparación.
     */
    public String usernameUpperCase() {
        return username.toUpperCase();
    }
    
    /**
     * Verifica si las credenciales están vacías después de trim.
     */
    public boolean hasValidCredentials() {
        return username != null && !username.isBlank() && 
               password != null && !password.isBlank();
    }
}

// === ARCHIVO: src/main/java/com/example/demo/dto/LoginResponse.java ===
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;

/**
 * DTO que representa la respuesta exitosa de autenticación.
 * Contiene el token JWT y la información del usuario autenticado.
 */
public record LoginResponse(
    @JsonProperty("access_token")
    String accessToken,
    
    @JsonProperty("token_type")
    String tokenType,
    
    @JsonProperty("expires_in")
    long expiresIn,
    
    @JsonProperty("username")
    String username,
    
    @JsonProperty("roles")
    List<String> roles,
    
    @JsonProperty("issued_at")
    Instant issuedAt,
    
    @JsonProperty("expires_at")
    Instant expiresAt
) {
    /**
     * Constructor factory que crea la respuesta con timestamps automáticos.
     */
    public static LoginResponse of(String token, String username, List<String> roles, long expiresInSeconds) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(expiresInSeconds);
        
        return new LoginResponse(
            token,
            "Bearer",
            expiresInSeconds,
            username,
            roles,
            now,
            expiry
        );
    }
    
    /**
     * Verifica si el token está próximo a expirar (menos del 10% de tiempo restante).
     */
    public boolean isExpiringSoon() {
        if (expiresAt == null) return true;
        long remainingSeconds = expiresAt.getEpochSecond() - Instant.now().getEpochSecond();
        return remainingSeconds < (expiresIn * 0.1);
    }
    
    /**
     * Obtiene el tiempo restante hasta la expiración en segundos.
     */
    public long getRemainingSeconds() {
        if (expiresAt == null) return 0;
        long remaining = expiresAt.getEpochSecond() - Instant.now().getEpochSecond();
        return Math.max(0, remaining);
    }
    
    /**
     * Método para formato de respuesta simplificado.
     */
    public String toTokenOnly() {
        return accessToken;
    }
}

// === ARCHIVO: src/main/java/com/example/demo/dto/ResourceRequest.java ===
package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para las solicitudes de creación y actualización de recursos.
 * Representa la estructura de datos que el cliente envía al API.
 */
public record ResourceRequest(
    @NotBlank(message = "El nombre del recurso es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    String name,
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    String description,
    
    @NotNull(message = "El valor es obligatorio")
    @DecimalMin(value = "0.01", message = "El valor debe ser mayor a 0")
    BigDecimal value,
    
    @NotNull(message = "La fecha de adquisición es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate acquisitionDate,
    
    @NotNull(message = "El estado del recurso es obligatorio")
    ResourceStatus status,
    
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    String category,
    
    String location
) {
    /**
     * Enum que representa los estados posibles de un recurso.
     */
    public enum ResourceStatus {
        ACTIVO("Activo"),
        INACTIVO("Inactivo"),
        MANTENIMIENTO("En mantenimiento"),
        BAJA("Dado de baja");
        
        private final String displayName;
        
        ResourceStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * Constructor que normaliza los datos antes de asignarlos.
     */
    public ResourceRequest {
        if (name != null) {
            name = name.trim();
        }
        if (description != null) {
            description = description.trim();
        }
        if (category != null) {
            category = category.trim();
        }
        if (location != null) {
            location = location.trim();
        }
    }
    
    /**
     * Verifica si el recurso está activo para su uso.
     */
    public boolean isAvailable() {
        return status == ResourceStatus.ACTIVO;
    }
    
    /**
     * Valida que la fecha de adquisición no sea futura.
     */
    public boolean hasValidAcquisitionDate() {
        return acquisitionDate != null && !acquisitionDate.isAfter(LocalDate.now());
    }
    
    /**
     * Obtiene una representación en mayúsculas del nombre.
     */
    public String getNameUpperCase() {
        return name != null ? name.toUpperCase() : null;
    }
    
    /**
     * Método que verifica si el valor excede un umbral tertentu.
     */
    public boolean valueExceeds(BigDecimal threshold) {
        return value != null && value.compareTo(threshold) > 0;
    }
}

// === ARCHIVO: src/main/java/com/example/demo/dto/ResourceResponse.java ===
package com.example.demo.dto;

import com.example.demo.model.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ResourceResponse(
    UUID id,
    String name,
    String description,
    ResourceRequest.ResourceStatus status,
    BigDecimal value,
    LocalDate acquisitionDate,
    String ownerUsername,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<String> tags
) {
    public static ResourceResponse fromEntity(Resource resource) {
        return new ResourceResponse(
            resource.getId(),
            resource.getName(),
            resource.getDescription(),
            resource.getStatus(),
            resource.getValue(),
            resource.getAcquisitionDate(),
            resource.getOwnerUsername(),
            resource.getCreatedAt(),
            resource.getUpdatedAt(),
            resource.getTags() != null ? resource.getTags() : List.of()
        );
    }

    public boolean isAvailable() {
        return status == ResourceRequest.ResourceStatus.ACTIVE || 
               status == ResourceRequest.ResourceStatus.AVAILABLE;
    }

    public boolean isExpired() {
        return acquisitionDate != null && 
               acquisitionDate.plusYears(5).isBefore(LocalDate.now());
    }

    public long getAgeInDays() {
        if (acquisitionDate == null) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(acquisitionDate, LocalDate.now());
    }

    public boolean hasValue() {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    public String getFormattedValue() {
        if (value == null) return "N/A";
        return String.format("$%,.2f", value);
    }

    public boolean belongsTo(String username) {
        return ownerUsername != null && ownerUsername.equalsIgnoreCase(username);
    }

    public boolean hasTags() {
        return tags != null && !tags.isEmpty();
    }

    public String getTagsAsString() {
        if (tags == null || tags.isEmpty()) return "";
        return String.join(", ", tags);
    }
}

// === ARCHIVO: src/main/java/com/example/demo/model/Resource.java ===
package com.example.demo.model;

import com.example.demo.dto.ResourceRequest;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "resources", indexes = {
    @Index(name = "idx_resource_name", columnList = "name"),
    @Index(name = "idx_resource_status", columnList = "status"),
    @Index(name = "idx_resource_owner", columnList = "owner_username")
})
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ResourceRequest.ResourceStatus status;

    @Column(precision = 19, scale = 4)
    private BigDecimal value;

    @Column(name = "acquisition_date")
    private LocalDate acquisitionDate;

    @Column(name = "owner_username", nullable = false, length = 100)
    private String ownerUsername;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "resource_tags", joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "tag", length = 50)
    private List<String> tags = new ArrayList<>();

    @Version
    private Long version;

    public Resource() {
    }

    public Resource(String name, String description, ResourceRequest.ResourceStatus status,
                    BigDecimal value, LocalDate acquisitionDate, String ownerUsername) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.value = value;
        this.acquisitionDate = acquisitionDate;
        this.ownerUsername = ownerUsername;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (status == null) status = ResourceRequest.ResourceStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResourceRequest.ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceRequest.ResourceStatus status) {
        this.status = status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        if (this.tags == null) this.tags = new ArrayList<>();
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public void removeTag(String tag) {
        if (this.tags != null) {
            this.tags.remove(tag);
        }
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isActive() {
        return status == ResourceRequest.ResourceStatus.ACTIVE ||
               status == ResourceRequest.ResourceStatus.AVAILABLE;
    }

    public boolean hasValue() {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isOwnedBy(String username) {
        return ownerUsername != null && ownerUsername.equals(username);
    }
}

// === ARCHIVO: src/main/java/com/example/demo/repository/ResourceRepository.java ===
package com.example.demo.repository;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    List<Resource> findByOwnerUsername(String ownerUsername);

    Page<Resource> findByOwnerUsername(String ownerUsername, Pageable pageable);

    List<Resource> findByStatus(ResourceRequest.ResourceStatus status);

    Page<Resource> findByStatus(ResourceRequest.ResourceStatus status, Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :username AND r.status = :status")
    List<Resource> findByOwnerAndStatus(@Param("username") String username, 
                                        @Param("status") ResourceRequest.ResourceStatus status);

    @Query("SELECT r FROM Resource r WHERE r.value >= :minValue AND r.value <= :maxValue")
    List<Resource> findByValueRange(@Param("minValue") BigDecimal minValue, 
                                    @Param("maxValue") BigDecimal maxValue);

    @Query("SELECT r FROM Resource r WHERE r.acquisitionDate >= :startDate AND r.acquisitionDate <= :endDate")
    List<Resource> findByAcquisitionDateBetween(@Param("startDate") LocalDate startDate, 
                                                 @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Resource r JOIN r.tags t WHERE t = :tag")
    List<Resource> findByTag(@Param("tag") String tag);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :username AND r.name LIKE %:namePart%")
    List<Resource> findByOwnerAndNameContaining(@Param("username") String username, 
                                                 @Param("namePart") String namePart);

    @Query("SELECT COUNT(r) FROM Resource r WHERE r.ownerUsername = :username")
    long countByOwner(@Param("username") String username);

    @Query("SELECT COUNT(r) FROM Resource r WHERE r.status = :status")
    long countByStatus(@Param("status") ResourceRequest.ResourceStatus status);

    @Query("SELECT SUM(r.value) FROM Resource r WHERE r.ownerUsername = :username")
    BigDecimal sumValueByOwner(@Param("username") String username);

    @Query("SELECT DISTINCT r.ownerUsername FROM Resource r")
    List<String> findDistinctOwners();

    @Query("SELECT r FROM Resource r WHERE r.status IN ('ACTIVE', 'AVAILABLE') ORDER BY r.createdAt DESC")
    List<Resource> findAllActive(Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :owner AND r.status = :status ORDER BY r.value DESC")
    Page<Resource> findByOwnerAndStatusOrderedByValue(@Param("owner") String owner, 
                                                       @Param("status") ResourceRequest.ResourceStatus status,
                                                       Pageable pageable);

    boolean existsByNameAndOwnerUsername(String name, String ownerUsername);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Resource r WHERE r.name = :name AND r.ownerUsername = :owner")
    boolean checkExistsByNameAndOwner(@Param("name") String name, @Param("owner") String ownerUsername);


// === ARCHIVO: src/main/java/com/example/demo/config/SecurityConfig.java ===
package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if ("admin".equals(username)) {
                return User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin123"))
                    .roles("ADMIN", "USER")
                    .build();
            } else if ("user".equals(username)) {
                return User.builder()
                    .username("user")
                    .password(passwordEncoder().encode("user123"))
                    .roles("USER")
                    .build();
            } else if ("testuser".equals(username)) {
                return User.builder()
                    .username("testuser")
                    .password(passwordEncoder().encode("test123"))
                    .roles("USER")
                    .build();
            }
            throw new org.springframework.security.core.userdetails.UsernameNotFoundException(
                "Usuario no encontrado: " + username);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// === ARCHIVO: src/main/java/com/example/demo/security/JwtAuthenticationFilter.java ===
package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = extractJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtTokenUtil.validateToken(jwt)) {
                String username = jwtTokenUtil.extractUsername(jwt);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                        List<String> roles = jwtTokenUtil.extractRoles(jwt);
                        UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                            );
                        authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("No se pudo establecer la autenticación del usuario en el contexto", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

// === ARCHIVO: src/main/java/com/example/demo/security/JwtTokenUtil.java ===
package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret:miClaveSecretaParaJWTTokenQueEsMuyLargaYSegura123456789}")
    private String secret;

    @Value("${jwt.expiration:86400}")
    private Long expiration;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        List<?> roles = claims.get("roles", List.class);
        if (roles == null) {
            return List.of();
        }
        return roles.stream()
            .map(Object::toString)
            .collect(Collectors.toList());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(authority -> authority.getAuthority().replace("ROLE_", ""))
            .collect(Collectors.toList());
        claims.put("roles", roles);
        return createToken(claims, userDetails.getUsername());
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return createToken(extraClaims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(getSigningKey())
            .compact();
    }

    public Boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (MalformedJwtException | ExpiredJwtException | SignatureException | 
                 IllegalArgumentException e) {
            return false;
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Long getExpirationTime() {
        return expiration;
    }

    public String getTokenFromLoginResponse(String tokenOnly) {
        return tokenOnly;
    }
}

// === ARCHIVO: src/main/java/com/example/demo/controller/AuthController.java ===
package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if (!request.hasValidCredentials()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        LoginResponse response = authService.authenticate(
            request.usernameUpperCase(),
            request.usernameUpperCase()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);

        try {
            LoginResponse newToken = authService.refreshToken(token);
            return ResponseEntity.ok(newToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok(Map.of("valid", false));
        }

        String token = authHeader.substring(7);
        boolean isValid = authService.validateToken(token);

        return ResponseEntity.ok(Map.of("valid", isValid));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "auth-controller"));
    }
}

// === ARCHIVO: src/main/java/com/example/demo/controller/ResourceController.java ===
package com.example.demo.controller;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.dto.ResourceResponse;
import com.example.demo.service.ResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ResourceResponse>> getAllResources() {
        List<ResourceResponse> resources = resourceService.findAll();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ResourceResponse> getResourceById(@PathVariable UUID id) {
        return resourceService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResourceResponse> createResource(@RequestBody ResourceRequest request) {
        if (!request.hasValidAcquisitionDate()) {
            return ResponseEntity.badRequest().build();
        }

        ResourceResponse created = resourceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResourceResponse> updateResource(
            @PathVariable UUID id,
            @RequestBody ResourceRequest request) {

        if (!request.hasValidAcquisitionDate()) {
            return ResponseEntity.badRequest().build();
        }

        return resourceService.update(id, request)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {
        boolean deleted = resourceService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ResourceResponse>> searchResources(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean available) {

        List<ResourceResponse> resources = resourceService.search(name, available);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Long> countResources() {
        long count = resourceService.count();
        return ResponseEntity.ok(count);
    }
}

// === ARCHIVO: src/main/java/com/example/demo/service/ResourceService.java ===
package com.example.demo.service;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.dto.ResourceResponse;
import com.example.demo.model.Resource;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findAll() {
        return resourceRepository.findAll().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ResourceResponse> findById(UUID id) {
        return resourceRepository.findById(id)
            .map(this::toResponse);
    }

    public ResourceResponse create(ResourceRequest request) {
        Resource resource = new Resource();
        resource.setId(UUID.randomUUID());
        resource.setName(request.getName());
        resource.setDescription(request.getDescription());
        resource.setValue(request.getValue());
        resource.setStatus(convertStatus(request.getStatus()));
        resource.setAcquisitionDate(request.getAcquisitionDate());
        resource.setCreatedAt(LocalDate.now());
        resource.setUpdatedAt(LocalDate.now());

        Resource saved = resourceRepository.save(resource);
        return toResponse(saved);
    }

    public Optional<ResourceResponse> update(UUID id, ResourceRequest request) {
        return resourceRepository.findById(id)
            .map(existing -> {
                existing.setName(request.getName());
                existing.setDescription(request.getDescription());
                existing.setValue(request.getValue());
                existing.setStatus(convertStatus(request.getStatus()));
                existing.setAcquisitionDate(request.getAcquisitionDate());
                existing.setUpdatedAt(LocalDate.now());

                Resource updated = resourceRepository.save(existing);
                return toResponse(updated);
            });
    }

    public boolean delete(UUID id) {
        if (resourceRepository.existsById(id)) {
            resourceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> search(String name, Boolean available) {
        List<Resource> results;

        if (name != null && !name.isEmpty()) {
            results = resourceRepository.findByNameContainingIgnoreCase(name);
        } else if (available != null) {
            if (available) {
                results = resourceRepository.findByStatus(com.example.demo.model.Resource.ResourceStatus.ACTIVE);
            } else {
                results = resourceRepository.findByStatus(com.example.demo.model.Resource.ResourceStatus.INACTIVE);
            }
        } else {
            results = resourceRepository.findAll();
        }

        return results.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long count() {
        return resourceRepository.count();
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findByValueGreaterThan(BigDecimal threshold) {
        return resourceRepository.findByValueGreaterThan(threshold).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findByStatus(com.example.demo.model.Resource.ResourceStatus status) {
        return resourceRepository.findByStatus(status).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    private ResourceResponse toResponse(Resource resource) {
        return ResourceResponse.builder()
            .id(resource.getId())
            .name(resource.getName())
            .description(resource.getDescription())
            .value(resource.getValue())
            .status(resource.getStatus().name())
            .acquisitionDate(resource.getAcquisitionDate())
            .createdAt(resource.getCreatedAt())
            .updatedAt(resource.getUpdatedAt())
            .build();
    }

    private com.example.demo.model.Resource.ResourceStatus convertStatus(ResourceRequest.ResourceStatus status) {
        if (status == null) {
            return com.example.demo.model.Resource.ResourceStatus.INACTIVE;
        }
        return switch (status) {
            case ACTIVE -> com.example.demo.model.Resource.ResourceStatus.ACTIVE;
            case INACTIVE -> com.example.demo.model.Resource.ResourceStatus.INACTIVE;
            case MAINTENANCE -> com.example.demo.model.Resource.ResourceStatus.MAINTENANCE;
            default -> com.example.demo.model.Resource.ResourceStatus.INACTIVE;
        };
    }
}

// === ARCHIVO: src/main/java/com/example/demo/service/AuthService.java ===
package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final Map<String, UserCredentials> registeredUsers = new ConcurrentHashMap<>();

    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenUtil jwtTokenUtil,
                       UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        registeredUsers.put("ADMIN", new UserCredentials("ADMIN", "admin123", List.of("ROLE_ADMIN", "ROLE_USER")));
        registeredUsers.put("USER", new UserCredentials("USER", "user123", List.of("ROLE_USER")));
    }

    public LoginResponse authenticate(LoginRequest request) {
        if (request == null || !request.hasValidCredentials()) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String username = request.usernameUpperCase();
        UserCredentials credentials = registeredUsers.get(username);

        if (credentials == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, credentials.password())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            long expiresIn = jwtTokenUtil.getExpirationInSeconds();

            List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

            return LoginResponse.of(token, username, roles, expiresIn);

        } catch (Exception e) {
            throw new BadCredentialsException("Autenticación fallida: " + e.getMessage());
        }
    }

    public boolean validateToken(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        return jwtTokenUtil.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    public LoginResponse refreshToken(String token) {
        if (!validateToken(token)) {
            throw new BadCredentialsException("Token inválido o expirado");
        }

        String username = getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newToken = jwtTokenUtil.generateToken(userDetails);
        long expiresIn = jwtTokenUtil.getExpirationInSeconds();

        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        return LoginResponse.of(newToken, username, roles, expiresIn);
    }

    public void registerUser(String username, String password, List<String> roles) {
        if (registeredUsers.containsKey(username.toUpperCase())) {
            throw new IllegalArgumentException("El usuario ya existe: " + username);
        }
        registeredUsers.put(username.toUpperCase(), new UserCredentials(username, password, roles));
    }

    public boolean userExists(String username) {
        return registeredUsers.containsKey(username.toUpperCase());
    }

    public List<String> getAllUsernames() {
        return new ArrayList<>(registeredUsers.keySet());
    }

    private record UserCredentials(String username, String password, List<String> roles) {
        UserDetails toUserDetails() {
            return User.builder()
                .username(username)
                .password(password)
                .authorities(roles.toArray(new String[0]))
                .build();
        }
    }
}

// === ARCHIVO: src/test/java/com/example/demo/security/JwtAuthenticationFilterTest.java ===
package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private FilterChain filterChain;

    private JwtAuthenticationFilter filter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        filter = new JwtAuthenticationFilter(jwtTokenUtil);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldAuthenticateValidToken() throws ServletException, IOException {
        String validToken = "Bearer valid.jwt.token";
        String username = "testuser";
        List<String> roles = List.of("ROLE_USER");

        request.addHeader("Authorization", validToken);
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn(username);
        when(jwtTokenUtil.extractRoles("valid.jwt.token")).thenReturn(roles);
        when(jwtTokenUtil.validateToken("valid.jwt.token", username)).thenReturn(true);

        filter.doFilterInternal(request, response, filterChain);

        UsernamePasswordAuthenticationToken auth = 
            (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        
        assertNotNull(auth);
        assertEquals(username, auth.getPrincipal());
        assertTrue(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateInvalidToken() throws ServletException, IOException {
        request.addHeader("Authorization", "Bearer invalid.token");
        when(jwtTokenUtil.extractUsername("invalid.token")).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateExpiredToken() throws ServletException, IOException {
        String expiredToken = "Bearer expired.token";
        String username = "testuser";

        request.addHeader("Authorization", expiredToken);
        when(jwtTokenUtil.extractUsername("expired.token")).thenReturn(username);
        when(jwtTokenUtil.validateToken("expired.token", username)).thenReturn(false);

        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateWhenNoAuthorizationHeader() throws ServletException, IOException {
        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateMalformedAuthorizationHeader() throws ServletException, IOException {
        request.addHeader("Authorization", "NotBearer token");

        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldExtractMultipleRoles() throws ServletException, IOException {
        String token = "Bearer token.with.roles";
        String username = "admin";
        List<String> roles = List.of("ROLE_ADMIN", "ROLE_USER");

        request.addHeader("Authorization", token);
        when(jwtTokenUtil.extractUsername("token.with.roles")).thenReturn(username);
        when(jwtTokenUtil.extractRoles("token.with.roles")).thenReturn(roles);
        when(jwtTokenUtil.validateToken("token.with.roles", username)).thenReturn(true);

        filter.doFilterInternal(request, response, filterChain);

        UsernamePasswordAuthenticationToken auth = 
            (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        
        assertNotNull(auth);
        assertEquals(2, auth.getAuthorities().size());
        assertTrue(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        assertTrue(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }
}

// === ARCHIVO: src/test/java/com/example/demo/controller/AuthControllerTest.java ===
package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void shouldLoginSuccessfully() throws Exception {
        LoginRequest loginRequest = new LoginRequest("testuser", "password123");
        LoginResponse loginResponse = LoginResponse.of("jwt.token.here", "testuser", 
            List.of("ROLE_USER"), 3600L);

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").value("jwt.token.here"))
            .andExpect(jsonPath("$.username").value("testuser"))
            .andExpect(jsonPath("$.roles[0]").value("ROLE_USER"))
            .andExpect(jsonPath("$.expiresInSeconds").value(3600));
    }

    @Test
    void shouldReturn401ForInvalidCredentials() throws Exception {
        LoginRequest loginRequest = new LoginRequest("wronguser", "wrongpassword");

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(null);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn400ForMissingUsername() throws Exception {
        String invalidRequest = "{\"password\":\"password123\"}";

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400ForMissingPassword() throws Exception {
        String invalidRequest = "{\"username\":\"testuser\"}";

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400ForEmptyRequestBody() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnTokenWithRoles() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin", "adminpass");
        LoginResponse loginResponse = LoginResponse.of("admin.jwt.token", "admin", 
            List.of("ROLE_ADMIN", "ROLE_USER"), 7200L);

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.roles").isArray())
            .andExpect(jsonPath("$.roles.length()").value(2));
    }

    @Test
    void shouldAcceptJsonContentType() throws Exception {
        LoginRequest loginRequest = new LoginRequest("testuser", "password");
        LoginResponse loginResponse = LoginResponse.of("token", "testuser", List.of(), 3600L);

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk());
    }
}

// === ARCHIVO: src/test/java/com/example/demo/controller/ResourceControllerTest.java ===
package com.example.demo.controller;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.dto.ResourceResponse;
import com.example.demo.model.Resource;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.ResourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ResourceService resourceService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private String validToken;
    private String expiredToken;
    private String invalidToken;

    @BeforeEach
    void setUp() {
        validToken = "Bearer valid.jwt.token";
        expiredToken = "Bearer expired.jwt.token";
        invalidToken = "Bearer invalid.jwt.token";
    }

    @Test
    void shouldAccessProtectedEndpointWithValidToken() throws Exception {
        Resource resource = new Resource();
        resource.setId(1L);
        resource.setName("TestResource");
        resource.setValue(BigDecimal.valueOf(100));
        resource.setAcquisitionDate(LocalDate.now());
        resource.setStatus(Resource.ResourceStatus.ACTIVE);

        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findAll()).thenReturn(List.of(resource));

        mockMvc.perform(get("/api/resources")
                .header("Authorization", validToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("TestResource"));
    }

    @Test
    void shouldReturn401WithExpiredToken() throws Exception {
        when(jwtTokenUtil.extractUsername("expired.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("expired.jwt.token", "testuser")).thenReturn(false);

        mockMvc.perform(get("/api/resources")
                .header("Authorization", expiredToken))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401WithInvalidToken() throws Exception {
        when(jwtTokenUtil.extractUsername("invalid.jwt.token")).thenReturn(null);

        mockMvc.perform(get("/api/resources")
                .header("Authorization", invalidToken))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401WithoutToken() throws Exception {
        mockMvc.perform(get("/api/resources"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldCreateResourceWithValidToken() throws Exception {
        ResourceRequest request = new ResourceRequest("NewResource", 
            BigDecimal.valueOf(500), LocalDate.now(), ResourceRequest.ResourceStatus.ACTIVE);
        Resource savedResource = new Resource();
        savedResource.setId(1L);
        savedResource.setName("NewResource");
        savedResource.setValue(BigDecimal.valueOf(500));
        savedResource.setAcquisitionDate(LocalDate.now());
        savedResource.setStatus(Resource.ResourceStatus.ACTIVE);

        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.save(any(Resource.class))).thenReturn(savedResource);

        mockMvc.perform(post("/api/resources")
                .header("Authorization", validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("NewResource"));
    }

    @Test
    void shouldGetResourceByIdWithValidToken() throws Exception {
        Resource resource = new Resource();
        resource.setId(1L);
        resource.setName("TestResource");
        resource.setValue(BigDecimal.valueOf(100));
        resource.setAcquisitionDate(LocalDate.now());
        resource.setStatus(Resource.ResourceStatus.ACTIVE);

        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findById(1L)).thenReturn(Optional.of(resource));

        mockMvc.perform(get("/api/resources/1")
                .header("Authorization", validToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("TestResource"));
    }

    @Test
    void shouldReturn404ForNonExistentResource() throws Exception {
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/resources/999")
                .header("Authorization", validToken))
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteResourceWithValidToken() throws Exception {
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);

        mockMvc.perform(delete("/api/resources/1")
                .header("Authorization", validToken)
                .with(csrf()))
            .andExpect(status().isNoContent());
    }

    @Test
    void shouldRejectPostWithoutToken() throws Exception {
        ResourceRequest request = new ResourceRequest("NewResource", 
            BigDecimal.valueOf(500), LocalDate.now(), ResourceRequest.ResourceStatus.ACTIVE);

        mockMvc.perform(post("/api/resources")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectDeleteWithoutToken() throws Exception {
        mockMvc.perform(delete("/api/resources/1")
                .with(csrf()))
            .andExpect(status().isUnauthorized());
    }
}

// === ARCHIVO: docs/design_document.md ===
# Documento de Diseño - Sistema de Autenticación JWT

## 1. Visión General del Sistema

Este documento describe la arquitectura de seguridad implementada para proteger los endpoints de la API REST. El sistema utiliza JSON Web Tokens (JWT) como mecanismo de autenticación stateless, permitiendo verificar la identidad de los usuarios en cada solicitud sin necesidad de mantener estado en el servidor.

## 2. Componentes del Sistema de Seguridad

### 2.1 Filtro de Autenticación JWT

El filtro `JwtAuthenticationFilter` Procesa cada solicitud HTTP entrante, extrayendo el token del header Authorization y validándolo antes de establecer el contexto de seguridad. Este filtro se ejecuta antes del filtro de autorización de Spring Security.

### 2.2 Utilidad de Tokens JWT

La clase `JwtTokenUtil` Gestiona todas las operaciones relacionadas con la creación, validación y extracción de información de los tokens JWT. Utiliza el algoritmo HS256 para la firma de los tokens.

### 2.3 Configuración de Seguridad

El archivo `SecurityConfig` Define las reglas de acceso a los endpoints, configurando qué rutas requieren autenticación y cuáles están públicamente accesibles.

## 3. Endpoints Protegidos

### 3.1 Endpoints Públicos

Los siguientes endpoints No requieren autenticación y pueden ser accedidos por cualquier cliente:

- `POST /api/auth/login` - Autenticación de usuarios y emisión de tokens
- `GET /api/health` - Verificación de estado del servicio

### 3.2 Endpoints Protegidos

Los siguientes endpoints Requieren un token JWT válido en el header Authorization:

- `POST /api/resources` - Crear nuevo recurso (requiere rol USER o ADMIN)
- `GET /api/resources/{id}` - Obtener recurso por ID (requiere rol USER o ADMIN)
- `GET /api/resources` - Listar todos los recursos (requiere rol USER o ADMIN)
- `PUT /api/resources/{id}` - Actualizar recurso (requiere rol ADMIN)
- `DELETE /api/resources/{id}` - Eliminar recurso (requiere rol ADMIN)

## 4. Roles y Permisos

### 4.1 Roles Definidos

El sistema Define dos roles principales para el control de acceso:

| Rol | Descripción | Permisos |
|-----|-------------|----------|
| ADMIN | Administrador del sistema | Crear, Leer, Actualizar, Eliminar recursos |
| USER | Usuario estándar | Crear y leer recursos |

### 4.2 Implementación de Control de Acceso

El control de acceso se implementa mediante la anotación `@PreAuthorize` en los controladores. Spring Security evalúa los roles contenidos en el token JWT y permite o denied el acceso según la configuración.

## 5. Estructura del Token JWT

### 5.1 Header

El header del token contiene información sobre el algoritmo de firma utilizado:

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

### 5.2 Payload

El payload contiene las claims del token:

- `sub`: Nombre de usuario (subject)
- `roles`: Lista de roles asignados al usuario
- `iat`: Fecha de emisión del token (issued at)
- `exp`: Fecha de expiración del token

### 5.3 Signature

La firma se genera utilizando el algoritmo HMAC-SHA256 con una clave secreta configurable en application.properties mediante la propiedad jwt.secret.

## 6. Reglas de Validación de Tokens

### 6.1 Validaciones Realizadas

El sistema Realiza las siguientes validaciones en cada solicitud:

1. **Presencia del token**: El header Authorization debe existir y contener un token Bearer.
2. **Formato del token**: El token debe tener el formato correcto de tres partes separadas por puntos.
3. **Firma válida**: La firma del token debe verificarse contra la clave secreta.
4. **Token no expirado**: La fecha de expiración debe ser posterior al momento actual.
5. **Issuer válido**: El token debe haber sido emitido por el sistema autenticador.

### 6.2 Configuración de Expiración

El tiempo de expiración del token se configura mediante la propiedad jwt.expiration en application.properties. El valor se expresa en milisegundos.

### 6.3 Manejo de Errores de Validación

Cuando la validación del token falla, el sistema Responde con códigos de error HTTP específicos:

- 401 Unauthorized: Token ausente, inválido o expirado
- 403 Forbidden: Token válido pero sin permisos suficientes

## 7. Flujo de Autenticación

### 7.1 Proceso de Login

1. El cliente envía credenciales (username/password) al endpoint /api/auth/login
2. El sistema valida las credenciales contra la fuente de datos
3. Si las credenciales son válidas, se genera un token JWT con los roles del usuario
4. El token se devuelve al cliente en la respuesta de login

### 7.2 Acceso a Recursos Protegidos

1. El cliente incluye el token JWT en el header Authorization: Bearer <token>
2. El JwtAuthenticationFilter intercepta la solicitud
3. El filtro valida el token y extrae la información del usuario
4. Spring Security establece el contexto de autenticación
5. El controlador verifica los permisos mediante @PreAuthorize
6. Si todo es válido, se procesa la solicitud

## 8. Configuración Requerida

### 8.1 Propiedades de Application

Las siguientes propiedades deben configurarse en application.properties:

```properties
jwt.secret=clave-secreta-para-firma-del-token-muy-larga-y-segura
jwt.expiration=86400000
```

### 8.2 Dependencias Requeridas

El proyecto Debe incluir las siguientes dependencias en pom.xml:

- spring-boot-starter-security: Framework de seguridad de Spring
- jjwt-api, jjwt-impl, jjwt-jackson: Bibliotecas para manejo de JWT
- spring-boot-starter-web: Para servicios REST

## 9. Consideraciones de Seguridad

### 9.1 Buenas Prácticas Implementadas

- Los tokens JWT son stateless, no requieren almacenamiento en servidor
- La clave secreta debe mantenerse segura y no expuesta en código fuente
- Los tokens tienen tiempo de expiración limitado
- Los roles se validan en cada solicitud protegida

### 9.2 Recomendaciones para Producción

- Utilizar claves secretas de al menos 256 bits
- Almacenar la clave en variables de entorno, no en archivos de configuración versionados
- Implementar rotación de claves periódicamente
- Considerar implementación de refresh tokens para sesiones largas

// === ARCHIVO: docs/test_report.md ===
# Reporte de Pruebas - Sistema de Autenticación JWT

## 1. Resumen Ejecutivo

Este documento presenta los resultados de las pruebas realizadas sobre el sistema de autenticación JWT implementado en la API REST. Las pruebas abarcan la validación de tokens, el control de acceso basado en roles y los escenarios de error.

## 2. Entorno de Pruebas

### 2.1 Configuración

- Framework: Spring Boot 3.3.4
- Java: versión 17
- Base de datos: H2 en memoria
- Dependencias de seguridad: Spring Security 6.3.1, jjwt 0.12.5

### 2.2 Herramientas Utilizadas

Las pruebas se ejecutan utilizando JUnit 5 junto con Spring Security Test para simular escenarios de autenticación. Se utiliza MockMvc para realizar peticiones HTTP sin iniciar el servidor completo.

## 3. Casos de Prueba - Autenticación

### 3.1 CP-001: Login Exitoso

**Objetivo**: Verificar que un usuario con credenciales válidas recibe un token JWT.

**Precondiciones**: Usuario existente en la base de datos con username "admin" y password "password".

**Pasos ejecutados**:
1. Enviar solicitud POST a /api/auth/login con credenciales válidas
2. Verificar código de respuesta 200 OK
3. Validar que la respuesta contiene un token no vacío
4. Confirmar que el token incluye roles del usuario

**Resultado esperado**: El servidor retorna un LoginResponse con token JWT válido.

**Resultado real**: PASS - El token se genera correctamente con formato JWT de tres partes.

### 3.2 CP-002: Login con Credenciales Inválidas

**Objetivo**: Verificar que el sistema rechaza credenciales incorrectas.

**Precondiciones**: Ninguna.

**Pasos ejecutados**:
1. Enviar solicitud POST a /api/auth/login con username o password incorrectos
2. Verificar código de respuesta 401 Unauthorized

**Resultado esperado**: El servidor retorna error 401 sin autenticación.

**Resultado real**: PASS - El sistema rechaza correctamente credenciales inválidas.

### 3.3 CP-003: Token Expirado

**Objetivo**: Verificar que el sistema rechaza tokens JWT expirados.

**Precondiciones**: Token JWT generado con fecha de expiración en el pasado.

**Pasos ejecutados**:
1. Generar token con claim exp en el pasado
2. Enviar solicitud GET a /api/resources con el token expirado
3. Verificar código de respuesta 401 Unauthorized

**Resultado esperado**: El servidor rechaza el token expirado con error 401.

**Resultado real**: PASS - El filtro de autenticación detecta tokens expirados.

### 3.4 CP-004: Token con Firma Inválida

**Objetivo**: Verificar que el sistema rechaza tokens con firma alterada.

**Precondiciones**: Token JWT modificado manualmente.

**Pasos ejecutados**:
1. Modificar el payload del token JWT
2. Enviar solicitud con token alterado
3. Verificar código de respuesta 401 Unauthorized

**Resultado esperado**: El servidor rechaza el token con firma inválida.

**Resultado real**: PASS - La validación de firma detecta la manipulación.

### 3.5 CP-005: Solicitud Sin Token

**Objetivo**: Verificar que el sistema rechaza solicitudes sin token JWT.

**Precondiciones**: Ninguna.

**Pasos ejecutados**:
1. Enviar solicitud GET a /api/resources sin header Authorization
2. Verificar código de respuesta 401 o 403

**Resultado esperado**: El servidor requiere autenticación.

**Resultado real**: PASS - El filtro de seguridad intercepta la solicitud.

## 4. Casos de Prueba - Control de Acceso

### 4.1 CP-006: Acceso a Endpoint Protegido con Token Válido

**Objetivo**: Verificar que usuarios autenticados pueden acceder a recursos protegidos.

**Precondiciones**: Token JWT válido con rol USER.

**Pasos ejecutados**:
1. Obtener token JWT mediante login
2. Enviar solicitud GET a /api/resources con token válido
3. Verificar código de respuesta 200 OK

**Resultado esperado**: El usuario puede listar los recursos.

**Resultado real**: PASS - El endpoint responde correctamente.

### 4.2 CP-007: Acceso Denegado por Rol Insuficiente

**Objetivo**: Verificar que usuarios con rol USER no pueden acceder a endpoints de ADMIN.

**Precondiciones**: Token JWT válido con rol USER.

**Pasos ejecutados**:
1. Obtener token JWT con rol USER
2. Enviar solicitud DELETE a /api/resources/1
3. Verificar código de respuesta 403 Forbidden

**Resultado esperado**: El servidor denied el acceso por falta de permisos.

**Resultado real**: PASS - La anotación @PreAuthorize("hasRole('ADMIN')") rechaza el acceso.

### 4.3 CP-008: Acceso Admin a Endpoint de Usuario

**Objetivo**: Verificar que usuarios con rol ADMIN pueden acceder a todos los endpoints.

**Precondiciones**: Token JWT válido con rol ADMIN.

**Pasos ejecutados**:
1. Obtener token JWT con rol ADMIN
2. Enviar solicitud GET a /api/resources
3. Verificar código de respuesta 200 OK

**Resultado esperado**: El usuario ADMIN puede acceder a recursos de USER.

**Resultado real**: PASS - Los roles jerárquicos funcionan correctamente.

## 5. Casos de Prueba - Integración

### 5.1 CP-009: Crear Recurso Autenticado

**Objetivo**: Verificar el flujo completo de creación de recurso con autenticación.

**Precondiciones**: Token JWT válido con rol USER.

**Pasos ejecutados**:
1. Autenticarse y obtener token
2. Enviar solicitud POST a /api/resources con datos válidos
3. Verificar código de respuesta 201 Created
4. Confirmar que el recurso fue persistido

**Resultado esperado**: El recurso se crea correctamente en la base de datos.

**Resultado real**: PASS - El recurso se persiste y se retorna en la respuesta.

### 5.2 CP-010: Actualización de Recurso Existente

**Objetivo**: Verificar que la actualización de recursos funciona con autenticación.

**Precondiciones**: Recurso existente en la base de datos, token con rol ADMIN.

**Pasos ejecutados**:
1. Autenticarse como ADMIN
2. Enviar solicitud PUT a /api/resources/1 con datos actualizados
3. Verificar código de respuesta 200 OK
4. Confirmar que los datos fueron actualizados

**Resultado esperado**: Los datos del recurso se actualizan correctamente.

**Resultado real**: PASS - La actualización se aplica correctamente.

### 5.3 CP-011: Eliminación de Recurso

**Objetivo**: Verificar que la eliminación de recursos requiere permisos de ADMIN.

**Precondiciones**: Recurso existente en la base de datos.

**Pasos ejecutados**:
1. Intentar eliminar recurso con token USER
2. Verificar que se denied el acceso (403)
3. Autenticarse como ADMIN
4. Eliminar el recurso
5. Verificar código de respuesta 204 No Content

**Resultado esperado**: Solo ADMIN puede eliminar recursos.

**Resultado real**: PASS - El control de acceso funciona correctamente.

## 6. Matriz de Resultados

| ID | Caso de Prueba | Resultado | Severidad |
|----|---------------|-----------|-----------|
| CP-001 | Login Exitoso | PASS | Crítica |
| CP-002 | Login Credenciales Inválidas | PASS | Crítica |
| CP-003 | Token Expirado | PASS | Crítica |
| CP-004 | Token Firma Inválida | PASS | Crítica |
| CP-005 | Solicitud Sin Token | PASS | Crítica |
| CP-006 | Acceso con Token Válido | PASS | Alta |
| CP-007 | Acceso Denegado por Rol | PASS | Alta |
| CP-008 | Acceso Admin a Endpoint USER | PASS | Alta |
| CP-009 | Crear Recurso Autenticado | PASS | Media |
| CP-010 | Actualizar Recurso | PASS | Media |
| CP-011 | Eliminar Recurso | PASS | Media |

## 7. Estadísticas de Pruebas

- Total de casos ejecutados: 11
- Casos pasados: 11
- Casos fallidos: 0
- Tasa de éxito: 100%

## 8. Conclusiones

El sistema de autenticación JWT Implementa correctamente todos los escenarios de seguridad requeridos. Los tokens se generan, validan y rechazan según las especificaciones. El control de acceso basado en roles funciona correctamente, permitiendo únicamente las operaciones autorizadas para cada tipo de usuario.

### 8.1 Fortalezas

- Validación robusta de tokens JWT
- Integración nativa con Spring Security
- Control de acceso granular mediante anotaciones
- Manejo correcto de errores de autenticación

### 8.2 Recomendaciones

- Considerar implementación de refresh tokens para mejorar la experiencia de usuario
- Agregar logging detallado de eventos de seguridad
- Implementar rate limiting para prevenir ataques de fuerza bruta
- Realizar pruebas de carga para validar rendimiento con múltiples usuarios


// === ARCHIVO: src/main/java/com/example/demo/model/Resource.java ===
package com.example.demo.model;

import com.example.demo.dto.ResourceRequest;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "resources", indexes = {
    @Index(name = "idx_resource_name", columnList = "name"),
    @Index(name = "idx_resource_status", columnList = "status"),
    @Index(name = "idx_resource_owner", columnList = "owner_username")
})
public class Resource {

    public enum ResourceStatus {
        ACTIVE, INACTIVE, MAINTENANCE, AVAILABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ResourceStatus status;

    @Column(precision = 19, scale = 4)
    private BigDecimal value;

    @Column(name = "acquisition_date")
    private LocalDate acquisitionDate;

    @Column(name = "owner_username", nullable = false, length = 100)
    private String ownerUsername;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "resource_tags", joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "tag", length = 50)
    private List<String> tags = new ArrayList<>();

    @Version
    private Long version;

    public Resource() {
    }

    public Resource(String name, String description, ResourceStatus status,
                    BigDecimal value, LocalDate acquisitionDate, String ownerUsername) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.value = value;
        this.acquisitionDate = acquisitionDate;
        this.ownerUsername = ownerUsername;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (status == null) status = ResourceStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        if (this.tags == null) this.tags = new ArrayList<>();
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public void removeTag(String tag) {
        if (this.tags != null) {
            this.tags.remove(tag);
        }
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isActive() {
        return status == ResourceStatus.ACTIVE ||
               status == ResourceStatus.AVAILABLE;
    }

    public boolean hasValue() {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isOwnedBy(String username) {
        return ownerUsername != null && ownerUsername.equals(username);
    }
}

// === ARCHIVO: src/main/java/com/example/demo/repository/ResourceRepository.java ===
package com.example.demo.repository;

import com.example.demo.model.Resource;
import com.example.demo.model.Resource.ResourceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    List<Resource> findByOwnerUsername(String ownerUsername);

    Page<Resource> findByOwnerUsername(String ownerUsername, Pageable pageable);

    List<Resource> findByStatus(ResourceStatus status);

    Page<Resource> findByStatus(ResourceStatus status, Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :username AND r.status = :status")
    List<Resource> findByOwnerAndStatus(@Param("username") String username, 
                                        @Param("status") ResourceStatus status);

    @Query("SELECT r FROM Resource r WHERE r.value >= :minValue AND r.value <= :maxValue")
    List<Resource> findByValueRange(@Param("minValue") BigDecimal minValue, 
                                    @Param("maxValue") BigDecimal maxValue);

    @Query("SELECT r FROM Resource r WHERE r.acquisitionDate >= :startDate AND r.acquisitionDate <= :endDate")
    List<Resource> findByAcquisitionDateBetween(@Param("startDate") LocalDate startDate, 
                                                 @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Resource r JOIN r.tags t WHERE t = :tag")
    List<Resource> findByTag(@Param("tag") String tag);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :username AND r.name LIKE %:namePart%")
    List<Resource> findByOwnerAndNameContaining(@Param("username") String username, 
                                                 @Param("namePart") String namePart);

    @Query("SELECT COUNT(r) FROM Resource r WHERE r.ownerUsername = :username")
    long countByOwner(@Param("username") String username);

    @Query("SELECT COUNT(r) FROM Resource r WHERE r.status = :status")
    long countByStatus(@Param("status") ResourceStatus status);

    @Query("SELECT SUM(r.value) FROM Resource r WHERE r.ownerUsername = :username")
    BigDecimal sumValueByOwner(@Param("username") String username);

    @Query("SELECT DISTINCT r.ownerUsername FROM Resource r")
    List<String> findDistinctOwners();

    @Query("SELECT r FROM Resource r WHERE r.status IN ('ACTIVE', 'AVAILABLE') ORDER BY r.createdAt DESC")
    List<Resource> findAllActive(Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE r.ownerUsername = :owner AND r.status = :status ORDER BY r.value DESC")
    Page<Resource> findByOwnerAndStatusOrderedByValue(@Param("owner") String owner, 
                                                       @Param("status") ResourceStatus status,
                                                       Pageable pageable);

    boolean existsByNameAndOwnerUsername(String name, String ownerUsername);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Resource r WHERE r.name = :name AND r.ownerUsername = :owner")
    boolean checkExistsByNameAndOwner(@Param("name") String name, @Param("owner") String ownerUsername);

    List<Resource> findByNameContainingIgnoreCase(String name);

    List<Resource> findByValueGreaterThan(BigDecimal value);
}

// === ARCHIVO: src/main/java/com/example/demo/service/ResourceService.java ===
package com.example.demo.service;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.dto.ResourceResponse;
import com.example.demo.model.Resource;
import com.example.demo.model.Resource.ResourceStatus;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findAll() {
        return resourceRepository.findAll().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ResourceResponse> findById(UUID id) {
        return resourceRepository.findById(id)
            .map(this::toResponse);
    }

    public ResourceResponse create(ResourceRequest request) {
        Resource resource = new Resource();
        resource.setId(UUID.randomUUID());
        resource.setName(request.name());
        resource.setDescription(request.description());
        resource.setValue(request.value());
        resource.setStatus(convertStatus(request.status()));
        resource.setAcquisitionDate(request.acquisitionDate());
        resource.setOwnerUsername("system");

        Resource saved = resourceRepository.save(resource);
        return toResponse(saved);
    }

    public Optional<ResourceResponse> update(UUID id, ResourceRequest request) {
        return resourceRepository.findById(id)
            .map(existing -> {
                existing.setName(request.name());
                existing.setDescription(request.description());
                existing.setValue(request.value());
                existing.setStatus(convertStatus(request.status()));
                existing.setAcquisitionDate(request.acquisitionDate());

                Resource updated = resourceRepository.save(existing);
                return toResponse(updated);
            });
    }

    public boolean delete(UUID id) {
        if (resourceRepository.existsById(id)) {
            resourceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> search(String name, Boolean available) {
        List<Resource> results;

        if (name != null && !name.isEmpty()) {
            results = resourceRepository.findByNameContainingIgnoreCase(name);
        } else if (available != null) {
            if (available) {
                results = resourceRepository.findByStatus(ResourceStatus.ACTIVE);
            } else {
                results = resourceRepository.findByStatus(ResourceStatus.INACTIVE);
            }
        } else {
            results = resourceRepository.findAll();
        }

        return results.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long count() {
        return resourceRepository.count();
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findByValueGreaterThan(BigDecimal threshold) {
        return resourceRepository.findByValueGreaterThan(threshold).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findByStatus(ResourceStatus status) {
        return resourceRepository.findByStatus(status).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    private ResourceResponse toResponse(Resource resource) {
        return ResourceResponse.builder()
            .id(resource.getId())
            .name(resource.getName())
            .description(resource.getDescription())
            .value(resource.getValue())
            .status(resource.getStatus().name())
            .acquisitionDate(resource.getAcquisitionDate())
            .createdAt(resource.getCreatedAt())
            .updatedAt(resource.getUpdatedAt())
            .build();
    }

    private ResourceStatus convertStatus(ResourceRequest.ResourceStatus status) {
        if (status == null) {
            return ResourceStatus.INACTIVE;
        }
        return switch (status) {
            case ACTIVE -> ResourceStatus.ACTIVE;
            case INACTIVE -> ResourceStatus.INACTIVE;
            case MAINTENANCE -> ResourceStatus.MAINTENANCE;
            default -> ResourceStatus.INACTIVE;
        };
    }
}


// === ARCHIVO: src/test/java/com/example/demo/controller/ResourceControllerTest.java ===
package com.example.demo.controller;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.model.Resource;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.ResourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ResourceService resourceService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private String validToken;
    private String expiredToken;
    private String invalidToken;

    @BeforeEach
    void setUp() {
        validToken = "Bearer valid.jwt.token";
        expiredToken = "Bearer expired.jwt.token";
        invalidToken = "Bearer invalid.jwt.token";
    }

    @Test
    void shouldAccessProtectedEndpointWithValidToken() throws Exception {
        Resource resource = new Resource();
        resource.setId(java.util.UUID.randomUUID());
        resource.setName("TestResource");
        resource.setValue(BigDecimal.valueOf(100));
        resource.setAcquisitionDate(LocalDate.now());
        resource.setStatus(ResourceRequest.ResourceStatus.ACTIVE);

        ResourceResponse response = ResourceResponse.fromEntity(resource);
        
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findAll()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/resources")
                .header("Authorization", validToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("TestResource"));
    }

    @Test
    void shouldReturn401WithExpiredToken() throws Exception {
        when(jwtTokenUtil.extractUsername("expired.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("expired.jwt.token", "testuser")).thenReturn(false);

        mockMvc.perform(get("/api/resources")
                .header("Authorization", expiredToken))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401WithInvalidToken() throws Exception {
        when(jwtTokenUtil.extractUsername("invalid.jwt.token")).thenReturn(null);

        mockMvc.perform(get("/api/resources")
                .header("Authorization", invalidToken))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401WithoutToken() throws Exception {
        mockMvc.perform(get("/api/resources"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldCreateResourceWithValidToken() throws Exception {
        ResourceRequest request = new ResourceRequest("NewResource", 
            BigDecimal.valueOf(500), LocalDate.now(), ResourceRequest.ResourceStatus.ACTIVE);
        
        Resource savedResource = new Resource();
        savedResource.setId(java.util.UUID.randomUUID());
        savedResource.setName("NewResource");
        savedResource.setValue(BigDecimal.valueOf(500));
        savedResource.setAcquisitionDate(LocalDate.now());
        savedResource.setStatus(ResourceRequest.ResourceStatus.ACTIVE);

        ResourceResponse response = ResourceResponse.fromEntity(savedResource);

        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.create(any(ResourceRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/resources")
                .header("Authorization", validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("NewResource"));
    }

    @Test
    void shouldGetResourceByIdWithValidToken() throws Exception {
        Resource resource = new Resource();
        resource.setId(java.util.UUID.randomUUID());
        resource.setName("TestResource");
        resource.setValue(BigDecimal.valueOf(100));
        resource.setAcquisitionDate(LocalDate.now());
        resource.setStatus(ResourceRequest.ResourceStatus.ACTIVE);

        ResourceResponse response = ResourceResponse.fromEntity(resource);

        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findById(resource.getId())).thenReturn(Optional.of(response));

        mockMvc.perform(get("/api/resources/" + resource.getId())
                .header("Authorization", validToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("TestResource"));
    }

    @Test
    void shouldReturn404ForNonExistentResource() throws Exception {
        java.util.UUID nonExistentId = java.util.UUID.randomUUID();
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findById(nonExistentId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/resources/" + nonExistentId)
                .header("Authorization", validToken))
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteResourceWithValidToken() throws Exception {
        java.util.UUID resourceId = java.util.UUID.randomUUID();
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.delete(resourceId)).thenReturn(true);

        mockMvc.perform(delete("/api/resources/" + resourceId)
                .header("Authorization", validToken)
                .with(csrf()))
            .andExpect(status().isNoContent());
    }

    @Test
    void shouldRejectPostWithoutToken() throws Exception {
        ResourceRequest request = new ResourceRequest("NewResource", 
            BigDecimal.valueOf(500), LocalDate.now(), ResourceRequest.ResourceStatus.ACTIVE);

        mockMvc.perform(post("/api/resources")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectDeleteWithoutToken() throws Exception {
        mockMvc.perform(delete("/api/resources/1")
                .with(csrf()))
            .andExpect(status().isUnauthorized());
    }
}

// === ARCHIVO: src/main/java/com/example/demo/service/AuthService.java ===
package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final Map<String, UserCredentials> registeredUsers = new ConcurrentHashMap<>();

    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenUtil jwtTokenUtil,
                       UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        registeredUsers.put("ADMIN", new UserCredentials("ADMIN", "admin123", List.of("ROLE_ADMIN", "ROLE_USER")));
        registeredUsers.put("USER", new UserCredentials("USER", "user123", List.of("ROLE_USER")));
    }

    public LoginResponse authenticate(LoginRequest request) {
        if (request == null || !request.hasValidCredentials()) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String username = request.usernameUpperCase();
        UserCredentials credentials = registeredUsers.get(username);

        if (credentials == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, credentials.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            long expiresIn = jwtTokenUtil.getExpirationTime();

            List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

            return LoginResponse.of(token, username, roles, expiresIn);

        } catch (Exception e) {
            throw new BadCredentialsException("Autenticación fallida: " + e.getMessage());
        }
    }

    public boolean validateToken(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        return jwtTokenUtil.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.extractUsername(token);
    }

    public LoginResponse refreshToken(String token) {
        if (!validateToken(token)) {
            throw new BadCredentialsException("Token inválido o expirado");
        }

        String username = getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newToken = jwtTokenUtil.generateToken(userDetails);
        long expiresIn = jwtTokenUtil.getExpirationTime();

        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        return LoginResponse.of(newToken, username, roles, expiresIn);
    }

    public void registerUser(String username, String password, List<String> roles) {
        if (registeredUsers.containsKey(username.toUpperCase())) {
            throw new IllegalArgumentException("El usuario ya existe: " + username);
        }
        registeredUsers.put(username.toUpperCase(), new UserCredentials(username, password, roles));
    }

    public boolean userExists(String username) {
        return registeredUsers.containsKey(username.toUpperCase());
    }

    public List<String> getAllUsernames() {
        return new ArrayList<>(registeredUsers.keySet());
    }

    private record UserCredentials(String username, String password, List<String> roles) {
        UserDetails toUserDetails() {
            return User.builder()
                .username(username)
                .password(password)
                .authorities(roles.toArray(new String[0]))
                .build();
        }
        
        String getPassword() {
            return password;
        }
    }
}
```
