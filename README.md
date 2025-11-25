# Sistema de Gestión de Inventario - API REST

Sistema de gestión de inventario desarrollado con Spring Boot, implementando autenticación JWT, autorización basada en roles y operaciones CRUD completas para productos, categorías y proveedores.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Security con JWT
- Spring Data JPA
- MySQL 8.0
- Maven
- Docker y Docker Compose
- Swagger/OpenAPI para documentación

## Requisitos Previos

### Para ejecución local

- Java JDK 17 o superior
- Maven 3.8 o superior
- MySQL 8.0
- Un IDE como IntelliJ IDEA, Eclipse o VS Code
- Postman (recomendado para probar la API)

### Para ejecución con Docker

- Docker Desktop instalado y corriendo
- Docker Compose

## Configuración del Entorno

### Paso 1: Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd inventory-api
```

### Paso 2: Configurar la Base de Datos MySQL

El proyecto incluye un script SQL completo con la estructura de la base de datos y datos de prueba en la carpeta `database/`.

**Opción A: Importar desde MySQL Workbench**

1. Abrir MySQL Workbench o tu cliente MySQL preferido
2. Crear una nueva base de datos llamada `inventory`
3. Seleccionar esa base de datos
4. Importar el archivo `database/inventory_db.sql`
5. Ejecutar el script

**Opción B: Importar desde terminal**

```bash
mysql -u root -p < database/inventory_db.sql
```

### Paso 3: Verificar application.properties

El archivo `src/main/resources/application.properties` debe tener esta configuración:

```properties
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/inventory
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:root}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:admin}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=Q9zE4Lk8T2mH9BvR3nX7pC1sV4gY6dW8Z0qR2tU5aS8fD1hJ3
jwt.expiration=3600000
```

## Ejecución Local

Una vez configurada la base de datos, ejecuta la aplicación con Maven:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

Puedes verificar que todo funcione correctamente accediendo a la documentación de Swagger:

`http://localhost:8080/swagger-ui.html`

## Docker

Docker Compose orquesta la aplicación Spring Boot y MySQL en contenedores separados. Al ejecutar por primera vez, el script SQL se carga automáticamente con todos los datos de prueba.

### Levantar la aplicación

```bash
docker-compose up --build
```

La aplicación estará disponible en `http://localhost:8080`

### Detener la aplicación

```bash
docker-compose down
```

## Credenciales de Prueba

```
Admin:
Username: admin
Password: admin123

User:
Username: user
Password: user123
```

## Prueba de Endpoints

### Usando Postman (Recomendado)

Postman es la herramienta más práctica para probar APIs REST, especialmente cuando trabajas con autenticación JWT.

#### Paso 1: Obtener el token de autenticación

Crea una nueva petición POST en Postman:

**URL:** `http://localhost:8080/api/auth/signin`

**Headers:**
```
Content-Type: application/json
```

**Body (raw JSON):**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Respuesta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "admin",
  "role": "ROLE_ADMIN",
  "message": "Login successful"
}
```

Copia el valor del campo `token` para usarlo en las siguientes peticiones.

#### Paso 2: Usar el token en las peticiones protegidas

Para cualquier endpoint protegido, agrega el token en los headers:

**Headers:**
```
Authorization: Bearer <tu-token-aquí>
Content-Type: application/json
```

**Ejemplo: Listar productos**

**URL:** `http://localhost:8080/api/product`  
**Method:** GET  
**Headers:** Incluir el token como se indicó arriba

#### Paso 3: Crear una colección en Postman

Para hacer las pruebas más rápidas:

1. Crear una colección nueva en Postman llamada "Inventory API"
2. Agregar una variable de colección llamada `token`
3. En la petición de signin, agregar este script en la pestaña Tests:
   ```javascript
   pm.collectionVariables.set("token", pm.response.json().token);
   ```
4. En las demás peticiones, usar `{{token}}` en el header Authorization


### Usando Swagger UI

También puedes probar todos los endpoints desde el navegador:

1. Accede a `http://localhost:8080/swagger-ui.html`
2. Haz clic en el endpoint `/api/auth/signin`
3. Prueba el login con las credenciales
4. Copia el token de la respuesta
5. Haz clic en el botón "Authorize" en la parte superior derecha
6. Pega el token en el campo (con el prefijo "Bearer ")
7. Ahora puedes probar cualquier endpoint desde la interfaz

## Documentación API

### Swagger UI

La documentación interactiva de la API está disponible en:

**URL:** `http://localhost:8080/swagger-ui.html`

### Endpoints principales

#### Autenticación

```
POST /api/auth/signup - Registrar un nuevo usuario
POST /api/auth/signin - Iniciar sesión y obtener token JWT
```

#### Productos

```
GET    /api/product      - Listar productos (paginado)
GET    /api/product/{id} - Obtener un producto específico
POST   /api/product      - Crear nuevo producto (requiere rol ADMIN)
PATCH  /api/product/{id} - Actualizar producto (requiere rol ADMIN)
DELETE /api/product/{id} - Eliminar producto (requiere rol ADMIN)
```

#### Categorías

```
GET    /api/category      - Listar todas las categorías
GET    /api/category/{id} - Obtener una categoría específica
POST   /api/category      - Crear nueva categoría (requiere rol ADMIN)
PATCH  /api/category/{id} - Actualizar categoría (requiere rol ADMIN)
DELETE /api/category/{id} - Eliminar categoría (requiere rol ADMIN)
```

#### Proveedores

```
GET    /api/supplier      - Listar todos los proveedores
GET    /api/supplier/{id} - Obtener un proveedor específico
POST   /api/supplier      - Crear nuevo proveedor (requiere rol ADMIN)
PATCH  /api/supplier/{id} - Actualizar proveedor (requiere rol ADMIN)
DELETE /api/supplier/{id} - Eliminar proveedor (requiere rol ADMIN)
```

## Ejecución de Pruebas

El proyecto incluye pruebas unitarias para validar la lógica de negocio de los servicios principales.

### Ejecutar todas las pruebas

```bash
mvn test
```

### Pruebas implementadas

**Test 01: Validación de nombre de categoría duplicado**

Método: `testSave_CategoryNameAlreadyExists_ThrowsBusinessException()`

Verifica que el sistema no permita crear una categoría con un nombre que ya existe en la base de datos.

**Test 02: Validación de eliminación de categoría con productos asociados**

Método: `testDelete_CategoryHasProducts_ThrowsBusinessException()`

Valida que no se pueda eliminar una categoría que tiene productos asociados.

## Cliente Web

El proyecto incluye un cliente web simple desarrollado en HTML y JavaScript para demostrar el consumo de la API.

**Acceso:** `http://localhost:8080/index.html`

**Funcionalidades:**
- Formulario de login
- Autenticación JWT
- Listado de productos
- Manejo de sesión
