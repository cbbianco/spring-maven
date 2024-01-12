# Prueba de Evaluación

# Indice
1. [Descripción](#Descripción)
2. [Operaciones](#Operaciones)
3. [Migración](#Migración)
4. [Pruebas](#Pruebas)

# Descripción
Proyecto trabajado con Maven, Java 11 y la versión de spring más actual que esta versión de java permite, 
El contexto utilizado para los endopint es: /productos 

# Operaciones 
Endpoint /api/v1/productos/guardar, el endpoint describe como se debería utilizar para utilizar utilización
- Uri: http://localhost:8080/
- Endpoint: **/api/v1/productos/guardar**

```bash
curl --location --request POST 'http://localhost:8080/productos/api/v1/productos/guardar' \
--header 'Content-Type: application/json' \
--data ''
```

# Migración

El proyecto cuenta con la posibilidad de generar las migraciones asociadas, por medio de la dependencia flyway, la bd
se crea al momento de levantar el proyecto en la base de build del mismo.

El archivo de la migración asociada se encuentra en resources/db/migration

###  Comando de ejecución
```bash
mvn flyway:migrate
```

# Pruebas Unitarias

Para la ejecución de las pruebas unitarias se debe ejecutar el siguiente comando, site/jacoco/index.html es la ruta para
desplegar el reporte de los test unitarios

###  Comando de ejecución
```bash
mvn clean test
```