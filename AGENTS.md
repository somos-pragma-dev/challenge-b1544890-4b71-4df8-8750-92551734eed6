# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de autenticación JWT en una API REST**.

| | |
|---|---|
| Tema | seguridad en api rest |
| Nivel | junior-l1 |
| Chapter | Generico |
| Especialidad | Inferido del contexto |
| Stack | Java / Spring Boot 3.3.4 |
| Patron arquitectonico | capas estándar con seguridad en filtro |
| Tiempo estimado | 8 horas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `el comando de build o arranque canonico del stack elegido` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `el comando de build o arranque canonico del stack elegido` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Diseño del mecanismo de autenticación**: Documento de diseño que describe el mecanismo de autenticación JWT, incluyendo los endpoints protegidos, roles y permisos, y reglas de validación.
- **Fase 2 — Implementación de la autenticación JWT**: API REST con autenticación JWT implementada, donde los usuarios deben autenticarse para acceder a los endpoints protegidos.
- **Fase 3 — Pruebas y validación del mecanismo de autenticación**: Reporte de pruebas que demuestra que el mecanismo de autenticación JWT funciona correctamente en diferentes escenarios.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Boilerplate del stack (1)

Sin esto el proyecto no compila ni arranca. **Es tu trabajo crearlo**, y no toca nada de lo pedagogico: es andamiaje del stack.

- [ ] **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### 2. Referencias colgando (16)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/example/demo/dto/ResourceResponse.java` — `ResourceStatus`
      ResourceStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.demo.model.ResourceStatus.
- [ ] `src/test/java/com/example/demo/controller/ResourceControllerTest.java` — `ResourceStatus`
      ResourceStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.demo.dto.ResourceStatus (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `src/test/java/com/example/demo/controller/ResourceControllerTest.java` — `ResourceResponse`
      ResourceResponse se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.example.demo.dto.ResourceResponse.
- [ ] `src/main/java/com/example/demo/model/Resource.java` — `ResourceRequest`
      El import com.example.demo.dto.ResourceRequest no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.findAll`
      Se invoca `findAll` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.findById`
      Se invoca `findById` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.name`
      Se invoca `name` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.description`
      Se invoca `description` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.value`
      Se invoca `value` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.status`
      Se invoca `status` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRequest.acquisitionDate`
      Se invoca `acquisitionDate` sobre `ResourceRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.save`
      Se invoca `save` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.existsById`
      Se invoca `existsById` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.deleteById`
      Se invoca `deleteById` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/ResourceService.java` — `ResourceRepository.count`
      Se invoca `count` sobre `ResourceRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/example/demo/service/AuthService.java` — `UserCredentials.getPassword`
      Se invoca `getPassword` sobre `UserCredentials`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (21)

- `pom.xml`
- `src/main/java/com/example/demo/DemoApplication.java`
- `src/main/resources/application.properties`
- `src/main/java/com/example/demo/dto/LoginRequest.java`
- `src/main/java/com/example/demo/dto/LoginResponse.java`
- `src/main/java/com/example/demo/dto/ResourceRequest.java`
- `src/main/java/com/example/demo/dto/ResourceResponse.java`
- `src/main/java/com/example/demo/model/Resource.java`
- `src/main/java/com/example/demo/repository/ResourceRepository.java`
- `src/main/java/com/example/demo/config/SecurityConfig.java`
- `src/main/java/com/example/demo/security/JwtAuthenticationFilter.java`
- `src/main/java/com/example/demo/security/JwtTokenUtil.java`
- `src/main/java/com/example/demo/controller/AuthController.java`
- `src/main/java/com/example/demo/controller/ResourceController.java`
- `src/main/java/com/example/demo/service/ResourceService.java`
- `src/main/java/com/example/demo/service/AuthService.java`
- `src/test/java/com/example/demo/security/JwtAuthenticationFilterTest.java`
- `src/test/java/com/example/demo/controller/AuthControllerTest.java`
- `src/test/java/com/example/demo/controller/ResourceControllerTest.java`
- `docs/design_document.md`
- `docs/test_report.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/example/demo`
- `src/main/java/com/example/demo/config`
- `src/main/java/com/example/demo/controller`
- `src/main/java/com/example/demo/dto`
- `src/main/java/com/example/demo/model`
- `src/main/java/com/example/demo/repository`
- `src/main/java/com/example/demo/security`
- `src/main/java/com/example/demo/service`
- `src/main/resources`
- `src/test/java/com/example/demo`

## Verificacion

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar con seguridad en filtro**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Implementar autenticacion JWT en una API REST con Spring Security

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
