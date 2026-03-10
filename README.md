# 🏪 Sistema de Gestión de Inventario — MCP + API REST

API REST y servidor **Model Context Protocol (MCP)** para la gestión de productos de un inventario, desarrollada con Spring Boot 4.0 y Java 21. Permite realizar operaciones CRUD sobre productos y exponerlas como herramientas consumibles por asistentes de IA (Claude Desktop, MCP Inspector, etc.).

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Spring AI MCP](https://img.shields.io/badge/Spring%20AI-MCP%20Server-blue)](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Build](#-instalación-y-build)
- [Modo 1 — Ejecutar como API REST](#-modo-1--ejecutar-como-api-rest)
- [Modo 2 — Ejecutar como servidor MCP (STDIO)](#-modo-2--ejecutar-como-servidor-mcp-stdio)
- [Modo 3 — Ejecutar como servidor MCP (SSE/HTTP)](#-modo-3--ejecutar-como-servidor-mcp-ssehttp)
- [Integración con Claude Desktop](#-integración-con-claude-desktop)
- [Testing con MCP Inspector](#-testing-con-mcp-inspector)
- [Testing con Postman (API REST)](#-testing-con-postman-api-rest)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Base de Datos H2](#-base-de-datos-h2)
- [Estructura del Proyecto](#-estructura-del-proyecto)

---

## 📖 Descripción

Sistema de inventario que expone operaciones CRUD de productos de dos formas:

1. **API REST tradicional** — consumible por cualquier cliente HTTP (Postman, frontend, etc.)
2. **Servidor MCP** — permite que asistentes de IA como **Claude** interactúen directamente con el inventario en lenguaje natural, consultando productos, creando registros o actualizando stock sin código adicional.

### ✨ Características Principales

- ✅ CRUD completo de productos
- ✅ Validación de datos con Bean Validation
- ✅ Paginación y ordenamiento de resultados
- ✅ Manejo centralizado de errores con `@RestControllerAdvice`
- ✅ Base de datos en memoria H2
- ✅ **Servidor MCP via STDIO y SSE** ⭐
- ✅ Herramientas MCP registradas con `@Tool` de Spring AI
- ✅ Colección de Postman lista para usar
- ✅ Tests de integración con JUnit 5

---

## 🛠️ Tecnologías Utilizadas

| Tecnología       | Versión   | Propósito                          |
|------------------|-----------|------------------------------------|
| Java             | 21        | Lenguaje de programación           |
| Spring Boot      | 4.0.2     | Framework principal                |
| Spring AI        | 1.x       | Integración MCP Server             |
| Spring Data JPA  | 4.0.2     | Persistencia de datos              |
| H2 Database      | Runtime   | Base de datos en memoria           |
| Maven            | 3.9+      | Gestión de dependencias            |
| Lombok           | 1.18.38   | Reducción de código boilerplate    |
| JUnit 5          | 5.x       | Testing automatizado               |

---

## 📦 Requisitos Previos

| Herramienta      | Versión mínima | Verificar con              |
|------------------|----------------|----------------------------|
| Java JDK         | 21             | `java -version`            |
| Maven            | 3.9            | `mvn -version`             |
| Node.js + npx    | 18+ (opcional) | `node -v` (para MCP Inspector) |
| Claude Desktop   | Última         | (para integración MCP)     |

> **Nota:** El proyecto incluye Maven Wrapper (`mvnw` / `mvnw.cmd`), por lo que Maven no es estrictamente requerido si usas el wrapper.

---

## 🚀 Instalación y Build

### 1. Clonar el repositorio

```bash
git clone https://github.com/Slifer1212/PruebaTenicaa.git
cd PruebaTenicaa
```

### 2. Compilar y empaquetar

```bash
# Linux / macOS
./mvnw clean package -DskipTests

# Windows
mvnw.cmd clean package -DskipTests
```

El JAR se generará en:

```
target/PruebaTenicaa-0.0.1-SNAPSHOT.jar
```

---

## 🌐 Modo 1 — Ejecutar como API REST

Levanta el servidor HTTP en el puerto **8080**.

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

O directamente con el JAR:

```bash
java -jar target/PruebaTenicaa-0.0.1-SNAPSHOT.jar
```

Verifica que esté corriendo:

```
http://localhost:8080/api/products
```

---

## 🤖 Modo 2 — Ejecutar como servidor MCP (STDIO)

Este modo expone las herramientas del inventario por **stdin/stdout**, que es el transporte estándar para integrar con Claude Desktop y otros clientes MCP.

```bash
java \
  -Dspring.ai.mcp.server.stdio=true \
  -Dspring.main.web-application-type=none \
  -Dlogging.pattern.console= \
  -jar target/PruebaTenicaa-0.0.1-SNAPSHOT.jar
```

> ⚠️ En modo STDIO **no** se abre ningún puerto HTTP. La comunicación ocurre por stdin/stdout. No verás output visible en la consola si está correctamente configurado.

**Parámetros clave:**

| Parámetro                                  | Propósito                                              |
|--------------------------------------------|--------------------------------------------------------|
| `-Dspring.ai.mcp.server.stdio=true`        | Habilita el transporte STDIO para MCP                  |
| `-Dspring.main.web-application-type=none`  | Desactiva el servidor HTTP (no necesario en modo STDIO)|
| `-Dlogging.pattern.console=`               | Silencia logs para no contaminar el canal STDIO        |

---

## 📡 Modo 3 — Ejecutar como servidor MCP (SSE/HTTP)

En este modo el servidor MCP es accesible vía **Server-Sent Events** en HTTP, útil para clientes MCP remotos o pruebas con el MCP Inspector.

```bash
java -jar target/PruebaTenicaa-0.0.1-SNAPSHOT.jar
```

El endpoint SSE estará disponible en:

```
http://localhost:8080/sse
```

---

## 🖥️ Integración con Claude Desktop

### Paso 1 — Localizar el archivo de configuración

| Sistema Operativo | Ruta                                                                 |
|-------------------|----------------------------------------------------------------------|
| macOS             | `~/Library/Application Support/Claude/claude_desktop_config.json`   |
| Windows           | `%APPDATA%\Claude\claude_desktop_config.json`                        |
| Linux             | `~/.config/Claude/claude_desktop_config.json`                        |

### Paso 2 — Agregar la configuración del servidor MCP

Abre el archivo y agrega la siguiente entrada dentro de `mcpServers`. Si el archivo no existe, créalo con este contenido:

```json
{
  "mcpServers": {
    "inventario-productos": {
      "command": "java",
      "args": [
        "-Dspring.ai.mcp.server.stdio=true",
        "-Dspring.main.web-application-type=none",
        "-Dlogging.pattern.console=",
        "-jar",
        "/ruta/absoluta/a/PruebaTenicaa/target/PruebaTenicaa-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

> ⚠️ Reemplaza `/ruta/absoluta/a/PruebaTenicaa/` con la ruta real donde clonaste el proyecto.

**Ejemplo en Windows:**
```json
"args": [
  "-Dspring.ai.mcp.server.stdio=true",
  "-Dspring.main.web-application-type=none",
  "-Dlogging.pattern.console=",
  "-jar",
  "C:\\Users\\TuUsuario\\PruebaTenicaa\\target\\PruebaTenicaa-0.0.1-SNAPSHOT.jar"
]
```

### Paso 3 — Reiniciar Claude Desktop

Cierra y vuelve a abrir Claude Desktop. Si la configuración es correcta, verás el ícono 🔧 debajo del área de prompt indicando que las herramientas del inventario están disponibles.

### Paso 4 — Usar en Claude Desktop

Ahora puedes hacer preguntas en lenguaje natural como:

```
"¿Cuántos productos hay en el inventario?"
"Crea un producto llamado 'Monitor LG' con precio 15000 y stock 10"
"Muéstrame todos los productos con stock menor a 5"
"Actualiza el precio del producto con ID 3 a 8500"
```

---

## 🔍 Testing con MCP Inspector

El **MCP Inspector** es una herramienta oficial para probar servidores MCP de forma visual, sin necesidad de Claude Desktop.

### Requisito
```bash
node -v   # Debe ser v18 o superior
```

### Ejecutar el Inspector

```bash
npx @modelcontextprotocol/inspector \
  java \
  -Dspring.ai.mcp.server.stdio=true \
  -Dspring.main.web-application-type=none \
  "-Dlogging.pattern.console=" \
  -jar \
  target/PruebaTenicaa-0.0.1-SNAPSHOT.jar
```

Abre tu navegador en:

```
http://localhost:5173
```

Desde la interfaz podrás:
- Ver todas las **tools** disponibles (operaciones de inventario)
- Ejecutar herramientas manualmente con parámetros
- Inspeccionar el protocolo MCP en tiempo real

---

## 📬 Testing con Postman (API REST)

La carpeta `postman/` del proyecto contiene una colección lista para importar.

### Importar la colección

1. Abre **Postman**
2. Haz click en **Import**
3. Selecciona el archivo dentro de `postman/`
4. ¡Listo! Todas las requests estarán disponibles preconfiguradas

> Asegúrate de que el servidor esté corriendo en modo API REST (puerto 8080) antes de ejecutar las requests.

---

## 📡 Endpoints de la API

Base URL: `http://localhost:8080/api`

| Método   | Endpoint              | Descripción                        |
|----------|-----------------------|------------------------------------|
| `GET`    | `/products`           | Listar todos los productos         |
| `GET`    | `/products/{id}`      | Obtener producto por ID            |
| `POST`   | `/products`           | Crear nuevo producto               |
| `PUT`    | `/products/{id}`      | Actualizar producto completo       |
| `PATCH`  | `/products/{id}`      | Actualizar producto parcialmente   |
| `DELETE` | `/products/{id}`      | Eliminar producto                  |

### Parámetros de paginación (GET /products)

| Parámetro | Tipo    | Default | Descripción                            |
|-----------|---------|---------|----------------------------------------|
| `page`    | Integer | 0       | Número de página (base 0)              |
| `size`    | Integer | 10      | Cantidad de elementos por página       |
| `sort`    | String  | `id`    | Campo por el cual ordenar             |
| `dir`     | String  | `asc`   | Dirección: `asc` o `desc`             |

### Ejemplo — Crear producto

```json
POST /api/products
Content-Type: application/json

{
  "name": "Laptop Dell XPS 15",
  "description": "Laptop profesional con Intel Core i7",
  "price": 85000.00,
  "stock": 5,
  "category": "Electrónica"
}
```

---

## 🗄️ Base de Datos H2

La consola web de H2 está disponible mientras el servidor corra en modo API REST:

```
URL:      http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Usuario:  sa
Password: (vacío)
```

---

## 🧪 Ejecutar Tests Automatizados

```bash
# Linux / macOS
./mvnw test

# Windows
mvnw.cmd test
```

---

## 📁 Estructura del Proyecto

```
PruebaTenicaa/
├── .mvn/wrapper/              # Maven Wrapper
├── postman/                   # Colección de Postman
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   │       ├── controller/    # REST Controllers
│   │   │       ├── service/       # Lógica de negocio + @Tool MCP
│   │   │       ├── repository/    # Spring Data JPA Repositories
│   │   │       ├── model/         # Entidades JPA
│   │   │       ├── dto/           # Data Transfer Objects
│   │   │       └── exception/     # Manejo centralizado de errores
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/...              # Tests de integración JUnit 5
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

---

## 🤝 Flujo MCP — ¿Cómo funciona?

```
Claude Desktop
     │
     │ (STDIO / SSE)
     ▼
MCP Server (este proyecto)
     │
     │ invoca @Tool methods
     ▼
ProductService → ProductRepository → H2 Database
```

1. Claude recibe una instrucción del usuario en lenguaje natural.
2. Decide qué herramienta MCP invocar (ej. `getProductById`, `createProduct`).
3. El servidor MCP ejecuta la lógica de negocio correspondiente.
4. Devuelve el resultado a Claude, que lo presenta al usuario.

---

## 📄 Licencia

MIT License — ver [LICENSE](LICENSE) para más detalles.
