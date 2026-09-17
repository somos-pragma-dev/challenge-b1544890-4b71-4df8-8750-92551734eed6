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