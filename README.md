# 🏪 Sistema de Gestión de Inventario - API REST

API REST para la gestión de productos de un inventario, desarrollada con Spring Boot 4.0 y Java 21.

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Ejecución](#-ejecución)
- [Testing con Postman (RECOMENDADO)](#-testing-con-postman-recomendado)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Ejemplos de Uso](#-ejemplos-de-uso)
- [Base de Datos H2](#-base-de-datos-h2)
- [Testing Automatizado](#-testing-automatizado)
- [Estructura del Proyecto](#-estructura-del-proyecto)

---

## 📖 Descripción

Sistema de inventario que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre productos. Implementa arquitectura por capas (Controller, Service, Repository) siguiendo las mejores prácticas de desarrollo con Spring Boot.

### ✨ Características Principales

- ✅ CRUD completo de productos
- ✅ Validación de datos con Bean Validation
- ✅ Paginación y ordenamiento de resultados
- ✅ Manejo centralizado de errores con @RestControllerAdvice
- ✅ Base de datos en memoria H2
- ✅ Documentación completa con Javadoc
- ✅ Tests de integración con JUnit 5
- ✅ **Colección de Postman lista para usar** ⭐

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 21 | Lenguaje de programación |
| Spring Boot | 4.0.2 | Framework principal |
| Spring Data JPA | 4.0.2 | Persistencia de datos |
| H2 Database | Runtime | Base de datos en memoria |
| Maven | 3.9.12 | Gestión de dependencias |
| Lombok | 1.18.38 | Reducción de código boilerplate |
| JUnit 5 | 5.x | Testing automatizado |

---

## 📦 Requisitos Previos

### Software Requerido

- **Java 21** o superior
```bash
