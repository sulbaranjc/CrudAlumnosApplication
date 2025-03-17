# CRUD de Alumnos con Spring Boot

Aplicación web simple (Spring Boot + Thymeleaf) para gestionar alumnos y calcular su promedio ponderado. Incluye persistencia via JDBC a MySQL y una opción en memoria para desarrollo rápido.

> Este proyecto es de carácter didáctico para alumnado de FP de Grado Superior: el código, comentarios y ejemplos están enfocados a aprendizaje y experimentación.

## Requisitos
- Java 17+
- Maven 3.8+
- MySQL 8 (o compatible)

## Configuración de base de datos
1) Crear la base y la tabla (usa los scripts proporcionados):

```bash
mysql -u <usuario> -p < schema.sql
mysql -u <usuario> -p crud_alumnos < src/main/resources/db/data.sql
```

2) Ajustar las credenciales/host de la conexión en `src/main/java/com/personal/CrudAlumnosApplication/repository/Conexion.java` (`URL`, `USER`, `PASSWORD`).

## Alternar repositorio (JDBC vs en memoria)
- JDBC (por defecto): en `service/AlumnoService.java` el constructor tiene `@Qualifier("alumnoDAOJdbc")`, que usa MySQL con `Conexion`.
- En memoria: cambia el `@Qualifier` a `alumnoDAOEnMemoria` para trabajar sin base de datos y con datos volátiles. No requiere `schema.sql` ni credenciales.

## Ejecución
```bash
mvn spring-boot:run
# o
mvn clean package
java -jar target/CrudAlumnosApplication-0.0.1-SNAPSHOT.jar
```
La app corre en `http://localhost:8080` (configurable en `src/main/resources/application.properties`).

## Uso rápido
- `GET /` o `/alumnos`: lista de alumnos.
- `GET /alumnos/nuevo`: formulario para crear.
- `POST /alumnos/guardar`: guarda nuevo alumno.
- `GET /alumnos/editar/{id}`: carga formulario para editar.
- `POST /alumnos/actualizar`: actualiza.
- `GET /alumnos/eliminar/{id}`: elimina.

## Estructura relevante
- `controller/AlumnoController.java`: endpoints MVC.
- `service/AlumnoService.java`: orquesta lógica y repositorios.
- `repository/AlumnoDAOJdbc.java`: persistencia MySQL.
- `repository/AlumnoDAOEnMemoria.java`: almacenamiento en memoria.
- `model/Alumno.java`: entidad y cálculo del promedio.
- `templates/index.html` y `templates/form.html`: vistas Thymeleaf con Bootstrap.
- `src/main/resources/db/schema.sql` y `data.sql`: scripts de inicialización.

## Tests
```bash
mvn test
```

## Próximos pasos sugeridos
- Externalizar credenciales usando propiedades o variables de entorno.
- Añadir validaciones y mensajes de error en formularios.
- Agregar paginación o filtrado en el listado.

## Archivos y funciones principales
- `CrudAlumnosApplication.java`: clase `main` que arranca Spring Boot.
- `controller/AlumnoController.java`: controla rutas web, usa `AlumnoService` y vistas Thymeleaf.
- `service/AlumnoService.java`: capa de servicio; orquesta lógica y el `AlumnoDAO` elegido.
- `repository/AlumnoDAO.java`: interfaz DAO usada por los repositorios concretos.
- `repository/AlumnoDAOJdbc.java`: implementación JDBC contra MySQL; usa `Conexion` y el modelo `Alumno`.
- `repository/AlumnoDAOEnMemoria.java`: implementación en memoria; usa `Alumno`, sin BD.
- `repository/Conexion.java`: singleton para obtener `Connection` JDBC MySQL; lo usa `AlumnoDAOJdbc`.
- `model/Alumno.java`: entidad con notas y cálculo de promedio; la usan controller, servicio y DAOs.
- `templates/index.html`: vista de listado; recibe `alumnos` desde el controller.
- `templates/form.html`: vista de formulario; enlaza con `AlumnoController` para crear/editar.
- `resources/application.properties`: config básica de app/puerto/Thymeleaf.
- `resources/db/schema.sql`: script para crear BD y tabla; se usa antes de levantar la app con MySQL.
- `resources/db/data.sql`: datos de ejemplo; se carga tras `schema.sql`.
- `pom.xml`: dependencias y configuración de build Maven.
