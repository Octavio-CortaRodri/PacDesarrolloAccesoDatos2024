# Aplicación de Gestión de Biblioteca

Esta es una aplicación desarrollada en Java que utiliza Hibernate para gestionar una biblioteca. Permite la manipulación de datos relacionados con libros, lectores (usuarios de la biblioteca) y las relaciones entre ellos, como los préstamos de libros.

## Requisitos Previos

Para ejecutar esta aplicación, necesitarás tener instalado:

- Java Development Kit (JDK) 8 o superior.
- XAMPP con MySQL.
- Eclipse IDE o cualquier otro entorno de desarrollo Java.

## Configuración de la Base de Datos con XAMPP

Antes de ejecutar la aplicación, asegúrate de tener XAMPP instalado y el servicio de MySQL en funcionamiento. Luego, sigue estos pasos para configurar la base de datos:

1. Inicia XAMPP y asegúrate de que los servicios de Apache y MySQL estén activados desde el panel de control.

2. Abre un navegador web y accede a `http://localhost/phpmyadmin`.

3. En phpMyAdmin, crea una nueva base de datos para la aplicación de gestión de biblioteca.

## Configuración de Hibernate

La aplicación utiliza Hibernate para interactuar con la base de datos MySQL de XAMPP. Asegúrate de que la configuración de Hibernate en el archivo `hibernate.cfg.xml` coincida con la configuración de tu base de datos MySQL en XAMPP.

## Ejecución de la Aplicación en Eclipse

1. Abre Eclipse y selecciona el espacio de trabajo donde se encuentra el proyecto de la aplicación de gestión de biblioteca.

2. Importa el proyecto a Eclipse si aún no está importado. Puedes hacerlo yendo a `File` > `Import...` y seleccionando `General` > `Existing Projects into Workspace`.

3. Una vez importado el proyecto, asegúrate de que todas las dependencias estén configuradas correctamente y que no haya errores en el proyecto.

4. Haz clic derecho en el archivo `Main.java` en el explorador de paquetes del proyecto y selecciona `Run As` > `Java Application`. Esto ejecutará la aplicación y mostrará un menú de opciones en la consola de Eclipse.

## Pruebas de Funcionalidad

Sigue las mismas instrucciones proporcionadas en el archivo `README.md` anterior para probar cada funcionalidad de la aplicación. Las operaciones de inserción, consulta, actualización y eliminación deberían funcionar correctamente con la base de datos de XAMPP.