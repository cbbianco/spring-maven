# Prueba de Evaluación

# Indice
1. [Descripción](#Descripción)
2. [Características](#Características)
3. [Operaciones](#Operaciones)
4. [Pruebas](#Pruebas)
5. [Patrones](#Patrones)

# Descripción
Proyecto trabajado con Maven, Java 11 y la versión de spring más actual que es permitida, 
El contexto utilizado para los endopint es: /productos 

# Características
Para los puntos estipulados tenemos:
#### 2.- El servicio debe permitir INSERT (post), UPDATE (put) y GETBYID (get) de un maestro / detalle de productos.
 - Insert
 - Update
 - Get

#### 4.- Implementar un “health check” del servicio para verificar su disponibilidad (Opcional)
Se Implemento por medio del YML, de está forma al momento de hacer comprobación se comprueba lo pertienente 
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health
      base-path: /
```
#### 5.- Se debe poder grabar la información del producto localmente (cualquier tipo de persistencia)
- a. La Lista de campos será definido por la persona evaluada (maestro y detalle).
- b. Utilizar migraciones para la creación de los objetos de la BD (Opcional)

Para la persistencia específicada y requerida en este punto se procedió ha dejarlos por BD, entonces se siguen estos pasos:
- Al Levantar el proyecto si no existe se cre la base de dato
- Para correr la migración se debe proceder a ser lo siguiente:

#### *Migración*

El proyecto cuenta con la posibilidad de generar las migraciones asociadas, por medio de la dependencia flyway, la bd
se crea al momento de levantar el proyecto en la base de build del mismo.

El archivo de la migración asociada se encuentra en resources/db/migration

###  Comando de ejecución
```bash
mvn flyway:migrate
```

# Operaciones 
Endpoint /api/v1/productos/guardar, el endpoint describe como se debería utilizar para utilizar utilización
- Uri: http://localhost:8080/
- Endpoint: **/api/v1/productos/guardar**

```bash
curl --location --request POST 'http://localhost:8080/productos/api/v1/productos/guardar' \
--header 'Content-Type: application/json' \
--data ''
```

# Pruebas Unitarias

Para la ejecución de las pruebas unitarias se debe ejecutar el siguiente comando, site/jacoco/index.html es la ruta para
desplegar el reporte de los test unitarios

###  Comando de ejecución
```bash
mvn clean test
```

# Patrones

- Para la gestión de las operaciones del producto y su detalle , para encapsular la sub operación se uso Patron Facade
