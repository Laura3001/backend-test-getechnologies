# API REST - Gestión de Directorio y Facturación

Backend robusto y contenerizado diseñado para la gestión eficiente de información personal y procesamiento de facturación. Este proyecto implementa una arquitectura de microservicios monolítica basada en **Spring Boot**, siguiendo principios de diseño SOLID y prácticas modernas de DevOps (CI/CD).

[![Build Status](https://github.com/Laura3001/backend-test-getechnologies/actions/workflows/maven.yml/badge.svg)](https://github.com/Laura3001/backend-test-getechnologies/actions)

## 📋 Descripción del Proyecto
El sistema expone una API RESTful que permite la administración del ciclo de vida de usuarios (Directorio) y sus transacciones financieras (Ventas). 

El objetivo principal del diseño fue garantizar la integridad referencial de los datos, la escalabilidad mediante contenedores Docker y la calidad del código a través de pruebas automatizadas y pipelines de integración continua.

## 🛠 Stack Tecnológico

* **Core:** Java 17 (LTS), Spring Boot 3.4.0
* **Datos:** H2 Database (In-Memory), Spring Data JPA, Hibernate.
* **Arquitectura:** API REST, Layered Architecture.
* **Calidad & Testing:** JUnit 5, Mockito.
* **DevOps & Infraestructura:** Docker, GitHub Actions (CI/CD Pipeline), Maven.

## ✨ Características Clave

### 1. Arquitectura y Diseño
* **Relaciones en Cascada:** Implementación de integridad referencial donde la eliminación de una entidad "Persona" gestiona automáticamente la limpieza de sus "Facturas" asociadas.
* **DTOs y Validaciones:** Uso de Jakarta Validation para asegurar la integridad de los datos de entrada.
* **Manejo Global de Excepciones:** Controlador centralizado (`GlobalExceptionHandler`) que estandariza las respuestas de error (404, 400, 500) en formato JSON.

### 2. Optimización y Rendimiento
* **Paginación:** Endpoints optimizados para consultas de grandes volúmenes de datos (`Pageable`).
* **Logging:** Trazabilidad completa de operaciones críticas mediante SLF4J.

### 3. DevOps e Integración Continua
* **Dockerizado:** El proyecto cuenta con un `Dockerfile` optimizado (Multi-stage build o Alpine base) listo para despliegue en cualquier orquestador.
* **GitHub Actions:** Pipeline automatizado que se activa con cada `push` a la rama principal, ejecutando:
    1.  Compilación del proyecto.
    2.  Ejecución de Test Unitarios.
    3.  Construcción de la imagen Docker.

## 🚀 Instalación y Ejecución

### Prerrequisitos
* Java 17 o superior (o Docker instalado).

### Opción A: Ejecución con Docker (Recomendado)
Para levantar el entorno completo sin instalar dependencias de Java:

docker build -t billing-api .
docker run -p 8080:8080 billing-api

### Opción B: Ejecución Local
./mvnw spring-boot:run


## 🔌 Documentación de Endpoints

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/directorio` | Registrar una nueva persona |
| `GET` | `/api/directorio/page` | Listar personas (Soporta `?page=0&size=10`) |
| `GET` | `/api/directorio/{id}` | Buscar persona por Identificación |
| `DELETE`| `/api/directorio/{id}` | Eliminar persona y sus facturas |
| `POST` | `/api/ventas/{id}/factura`| Registrar factura a usuario |
| `GET` | `/api/ventas/{id}/factura`| Consultar historial de facturas |
