# Implementación de autenticación JWT en una API REST

La empresa necesita asegurar sus servicios de API REST para garantizar que solo usuarios autorizados puedan acceder a ellos. Para ello, se ha decidido implementar un sistema de autenticación basado en JWT (JSON Web Tokens). El objetivo es diseñar y aplicar un mecanismo de autenticación que proteja los endpoints de la API, asegurando que los usuarios deben autenticarse antes de poder acceder a los recursos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | seguridad en api rest |
| **Nivel** | junior-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Diseño del mecanismo de autenticación

**Objetivo:** Definir los requerimientos y el diseño del sistema de autenticación JWT.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los endpoints que requieren protección.
- Definir los roles y permisos necesarios para acceder a los diferentes endpoints.
- Establecer las reglas de validación para los tokens JWT.

**Entregable:** Documento de diseño que describe el mecanismo de autenticación JWT, incluyendo los endpoints protegidos, roles y permisos, y reglas de validación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la duración y la forma de almacenamiento de los tokens.
- Piensa en cómo manejar la expiración y renovación de tokens.

</details>

### Fase 2: Implementación de la autenticación JWT

**Objetivo:** Implementar el mecanismo de autenticación JWT en la API REST.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Crear el servicio de autenticación que genera y valida los tokens JWT.
- Integrar el servicio de autenticación con los endpoints de la API.
- Asegurar que los usuarios deben autenticarse antes de acceder a los recursos protegidos.

**Entregable:** API REST con autenticación JWT implementada, donde los usuarios deben autenticarse para acceder a los endpoints protegidos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza bibliotecas y herramientas adecuadas para generar y validar tokens JWT.
- Asegúrate de que la implementación sigue las reglas de validación definidas en la fase anterior.

</details>

### Fase 3: Pruebas y validación del mecanismo de autenticación

**Objetivo:** Realizar pruebas para asegurar que el mecanismo de autenticación JWT funciona correctamente.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Crear casos de prueba para verificar la generación y validación de tokens JWT.
- Probar la autenticación en diferentes escenarios, incluyendo tokens válidos, expirados y no válidos.
- Asegurar que los endpoints protegidos solo son accesibles con tokens válidos.

**Entregable:** Reporte de pruebas que demuestra que el mecanismo de autenticación JWT funciona correctamente en diferentes escenarios.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de prueba para simular diferentes escenarios de autenticación.
- Asegúrate de cubrir todos los casos de prueba definidos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un token JWT y cómo se utiliza en la autenticación?
- **paraQueSirve**: ¿Para qué sirve la autenticación JWT en una API REST?
- **comoSeUsa**: ¿Cómo se implementa la autenticación JWT en una API REST?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar la autenticación JWT?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de la autenticación JWT en una API REST?

## Criterios de Evaluacion

- Diseño del mecanismo de autenticación JWT.
- Implementación del servicio de autenticación.
- Integración de la autenticación con los endpoints de la API.
- Pruebas y validación del mecanismo de autenticación.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
el comando de build o arranque canonico del stack elegido
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
