# Aplicación de Gestión de Biblioteca

Esta es una aplicación desarrollada en Java que utiliza Hibernate para gestionar una biblioteca. Permite la manipulación de datos relacionados con libros, lectores (usuarios de la biblioteca) y las relaciones entre ellos, como los préstamos de libros.

## Requisitos Previos

Para ejecutar esta aplicación, necesitaremos tener instalado:

- Java Development Kit (JDK) 8 o superior.
- XAMPP con MySQL.
- Eclipse IDE o cualquier otro entorno de desarrollo Java.

## Configuración de la Base de Datos con XAMPP

Antes de ejecutar la aplicación, nos aseguraremos de tener XAMPP instalado y el servicio de MySQL en funcionamiento. Sigue estos pasos para configurar la base de datos:

1. Inicia XAMPP y asegúrate de que los servicios de Apache y MySQL estén activados desde el panel de control.
2. Abre un navegador web y accede a [http://localhost/phpmyadmin](http://localhost/phpmyadmin). (Aqui verás como se creara la BBDD cuando ejecutemos el proyecto)

## Configuración de Hibernate

La aplicación utiliza Hibernate para interactuar con la base de datos de XAMPP. Nos aseguraremos de que la configuración de Hibernate en el archivo `hibernate.cfg.xml` coincida con la configuración de la base de datos en XAMPP.

## Ejecución de la Aplicación en Eclipse

1. Abre Eclipse y selecciona el espacio de trabajo donde se encuentra el proyecto de la aplicación de gestión de biblioteca.
2. Importa el proyecto a Eclipse si aún no está importado. Puedes hacerlo yendo a `File > Import...` y seleccionando `General > Existing Projects into Workspace`.
3. Haz clic derecho en el archivo `Main.java` en el explorador de paquetes del proyecto y selecciona `Run As > Java Application`. Esto ejecutará la aplicación y mostrará un menú de opciones en la consola de Eclipse.

## Pruebas de Funcionalidad

Para probar las funcionalidades de la aplicación, seguiremos las instrucciones en la consola de Eclipse. Las operaciones disponibles son:

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/1.png)



1. **Insertar Libro**: Permite al usuario agregar un nuevo libro al sistema proporcionando su título, autor y año de publicación. Los datos ingresados por el usuario se validan para garantizar la consistencia y precisión. Una vez validados, el libro se guarda en la base de datos y se confirma al usuario su inserción exitosa.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/2.1.png)
![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/2.2.png)



2. **Insertar Lector**: Ofrece la posibilidad de registrar un nuevo lector en el sistema, solicitando su nombre, apellido y correo electrónico. Los datos proporcionados por el usuario se verifican para asegurar su validez antes de almacenarlos. Una vez guardados, se informa al usuario sobre la correcta inserción del nuevo lector en la base de datos.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/3.png)
![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/4.png)



3. **Listado de Libros**: Permite al usuario visualizar todos los libros disponibles en el sistema. Se realiza una consulta a la base de datos para recuperar los registros de libros. Los títulos de los libros junto con sus autores se presentan ordenadamente al usuario, asegurando que la lista esté actualizada.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/5.png)



4. **Listado de Lectores**: Proporciona una lista de todos los lectores registrados en el sistema para su visualización por parte del usuario. Se realiza una consulta a la base de datos para recuperar los registros de lectores. Los nombres y apellidos de los lectores se muestran ordenadamente al usuario, garantizando que la lista esté actualizada.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/6.png)



5. **Ver Libro por ID**: Permite al usuario obtener detalles completos de un libro específico proporcionando su ID. Se solicita al usuario que ingrese el ID del libro deseado. Se realiza una consulta a la base de datos para recuperar los detalles del libro correspondiente al ID proporcionado. Se muestran todos los detalles del libro al usuario, incluyendo título, autor, año de publicación y disponibilidad.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/7.png)



6. **Ver Lector por ID**: Ofrece la posibilidad de ver los detalles completos de un lector específico proporcionando su ID. Se solicita al usuario que ingrese el ID del lector deseado. Se realiza una consulta a la base de datos para recuperar los detalles del lector correspondiente al ID proporcionado. Se muestran todos los detalles del lector al usuario, incluyendo nombre, apellido y correo electrónico.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/8.png)



7. **Gestión de Préstamos**:
   - **Asignar libro a lector**: El usuario selecciona esta opción para asignar un libro a un lector específico. El sistema solicita el ID del libro y del lector involucrados en el préstamo. Verifica si el libro está disponible para préstamo y, de ser así, lo asigna al lector seleccionado. Actualiza el estado del libro a no disponible y guarda los cambios en la base de datos.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/9.1.png)

   - **Registrar devolución**: Cuando un libro prestado se devuelve, el usuario elige esta opción. El sistema solicita el ID del libro devuelto. Verifica si el libro estaba prestado y, de ser así, lo marca como disponible nuevamente. Actualiza el estado del libro en la base de datos y confirma la devolución al usuario.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/9.2.png)



7. **Gestión de Libros y Lectores (CRUD)**:
   - **Crear libro**: El usuario inicia el proceso proporcionando detalles como título, autor y año de publicación. El sistema crea un nuevo registro de libro con la información proporcionada. Garantiza la consistencia y validez de los datos antes de almacenarlos en la base de datos.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.1.png)

   - **Leer libro**: Permite al usuario obtener detalles específicos de un libro proporcionando su ID. El sistema recupera y muestra todos los detalles del libro consultado. Ofrece una experiencia de usuario clara y detallada para comprender mejor el libro seleccionado.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.2.png)
     
   - **Actualizar libro**: Facilita la actualización de detalles de un libro seleccionado mediante su ID. El usuario puede modificar el título, autor, año de publicación y disponibilidad del libro. El sistema actualiza los datos del libro en la base de datos, asegurando la coherencia y validez de la información.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.3.png)
![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.4.png)
 
   - **Borrar libro**: Permite al usuario eliminar un libro específico proporcionando su ID. El sistema elimina permanentemente el registro del libro de la base de datos. Garantiza una experiencia de usuario fluida y sin ambigüedades durante el proceso de eliminación.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.5.png)
![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.6.png)

   - **Crear lector**: Similar al proceso de creación de libros, el usuario proporciona detalles como nombre, apellido y correo electrónico. El sistema crea un nuevo registro de lector con la información proporcionada. Asegura la integridad y coherencia de los datos del nuevo lector antes de almacenarlos en la base de datos.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.7.png)

   - **Leer lector**: Permite al usuario obtener detalles específicos de un lector proporcionando su ID. El sistema recupera y muestra todos los detalles del lector consultado. Ofrece una visión completa del perfil del lector seleccionado para comprender mejor su información.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.8.png)

   - **Actualizar lector**: Facilita la actualización de detalles de un lector seleccionado mediante su ID. El usuario puede modificar el nombre, apellido y correo electrónico del lector. El sistema actualiza los datos del lector en la base de datos, manteniendo la coherencia entre la información original y la actualizada.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.9.png)

   - **Borrar lector**: Permite al usuario eliminar un lector específico proporcionando su ID. El sistema elimina permanentemente el registro del lector de la base de datos. Garantiza una experiencia de usuario sólida y sin ambigüedades durante el proceso de eliminación.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.10.png)
![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/10.11.png)



7. **Consultas en la Base de Datos**:
   - **Libros actualmente prestados a un lector**: Solicita al usuario el ID del lector para obtener los libros que actualmente posee. El sistema consulta la base de datos y muestra los libros prestados al lector que no están disponibles para otros.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/11.1.png)

   - **Libros disponibles para préstamo**: Realiza una consulta en la base de datos para obtener todos los libros que están disponibles para préstamo. Muestra al usuario la lista de libros disponibles, proporcionando información detallada sobre cada uno.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/11.2.png)

   - **Historial de préstamos por lector**: Solicita al usuario el ID del lector para obtener su historial de préstamos. El sistema consulta la base de datos y muestra todos los libros prestados al lector en el pasado, ofreciendo una visión completa de su historial de préstamos.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/11.3.png)
     


7. **Salir**: Cuando el usuario elige esta opción, indica su intención de salir del programa y finalizar la sesión. El sistema muestra un mensaje de despedida o confirmación para asegurarse de que el usuario realmente desea salir. Antes de salir, el sistema cierra todas las conexiones de base de datos y recursos utilizados para garantizar una terminación limpia. Se proporciona una experiencia de usuario clara y satisfactoria, asegurándose de que el usuario esté informado sobre el fin del programa.

![](https://github.com/Octavio-CortaRodri/PacDesarrolloAccesoDatos2024/blob/main/IMAGENES%20README.md/12.png)
