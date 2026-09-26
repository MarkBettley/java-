# Spring Boot con Gradle

Aplicacion backend desarrollada con Spring Boot y Gradle durante la formacion Backend Java de EBAC.

## Funcionalidad

La aplicacion expone un endpoint REST para generar mensajes personalizados.

La arquitectura utiliza:

- MensajeController como capa HTTP.
- MensajeService como capa de negocio.
- Inyeccion de dependencias mediante Spring.
- Cache de resultados mediante Cacheable.

## Endpoint

GET /mensajes?nombre=Marco

Ejemplo de respuesta:

Hola Marco, este mensaje viene de la capa de negocio.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Cache
- Gradle
- JUnit Platform

## Ejecutar

    ./gradlew bootRun

## Ejecutar pruebas

    ./gradlew test

## Autor

Marco Antonio Hernandez Banos
