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