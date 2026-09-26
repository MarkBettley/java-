# Biblioteca Java - Proyecto 2 EBAC

Aplicación de biblioteca desarrollada en Java como evolución del Proyecto 1.

Esta versión incorpora persistencia en MySQL mediante JDBC, operaciones CRUD, patrones de diseño y pruebas automatizadas con JUnit 5.

## Tecnologías utilizadas

- Java 11
- Gradle
- MySQL 8
- JDBC
- JUnit 5
- Git

## Entidades

La aplicación administra las siguientes entidades:

- Author
- Book
- Library
- User

## Persistencia

Los datos se almacenan en la base de datos MySQL `biblioteca_ebac`.

Las tablas utilizadas son `authors`, `books`, `libraries` y `users`.

La tabla `books` utiliza claves foráneas para relacionar cada libro con un autor y una biblioteca.

El script de creación se encuentra en `database/schema.sql`.

## CRUD

Se implementaron operaciones Create, Read, Update y Delete mediante:

- AuthorDAO
- BookDAO
- LibraryDAO
- UserDAO

## Patrones de diseño

### Singleton

`DatabaseConnection` implementa Singleton para centralizar la configuración y obtención de conexiones JDBC.

### DAO

Las clases DAO separan la lógica de acceso y persistencia de datos del resto de la aplicación.

## Arquitectura

La aplicación está organizada en los paquetes:

- `dao`: acceso y persistencia de datos.
- `database`: conexión con MySQL.
- `model`: entidades del dominio.
- `service`: lógica de servicio.
- `exception`: excepciones de la aplicación.
- `Main`: demostración de ejecución.

## Base de datos

El esquema se encuentra en:

`database/schema.sql`

En sistemas donde MySQL root utiliza autenticación del sistema puede cargarse con:

`sudo mysql < database/schema.sql`

También puede utilizarse un usuario MySQL con permisos suficientes.

### Configuración de credenciales

La conexión permite configurar las credenciales de MySQL mediante variables de entorno:

`DB_USER`

`DB_PASSWORD`

Por defecto, el entorno local de desarrollo utiliza el usuario `ebac`. Para utilizar credenciales diferentes:

`export DB_USER=usuario_mysql`

`export DB_PASSWORD=contraseña_mysql`

Las credenciales utilizadas en producción no deben almacenarse directamente en el código fuente.

## Pruebas

El proyecto utiliza JUnit 5 e incluye pruebas para:

- patrón Singleton
- conexión JDBC
- CRUD de Author
- CRUD de Book
- CRUD de Library
- CRUD de User

Para ejecutar las pruebas:

`./gradlew clean test`

El proyecto cuenta con 6 pruebas automatizadas.

## Ejecución

Con MySQL activo y la base configurada:

`./gradlew run`

La aplicación demuestra persistencia real mediante JDBC y MySQL. Los datos utilizados durante la demostración son eliminados al finalizar para permitir múltiples ejecuciones.

## Autor

Marco Antonio Hernández Baños
