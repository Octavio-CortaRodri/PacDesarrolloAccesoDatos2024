import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

  private static SessionFactory factory;

  public static void main(String[] args) {
    // Configuración de Hibernate desde el archivo hibernate.cfg.xml
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    factory = configuration.buildSessionFactory();

    // Creación del objeto Scanner para entrada de datos
    Scanner sc = new Scanner(System.in);
    int opcion;

    // Ciclo principal del menú
    do {
      // Mostrar el menú
      mostrarMenu();
      // Leer la opción seleccionada por el usuario
      opcion = sc.nextInt();
      // Realizar acciones según la opción seleccionada
      switch (opcion) {
        case 1:
          // Insertar un nuevo libro en la base de datos
          insertarLibro();
          break;
        case 2:
          // Insertar un nuevo lector en la base de datos
          insertarLector();
          break;
        case 3:
          // Listar todos los libros almacenados en la base de datos
          listarLibros();
          break;
        case 4:
          // Listar todos los lectores almacenados en la base de datos
          listarLectores();
          break;
        case 5:
          // Ver detalles de un libro por su ID
          verLibroPorID(sc);
          break;
        case 6:
          // Ver detalles de un lector por su ID
          verLectorPorID(sc);
          break;
        case 7:
          // Salir del programa
          salir();
          break;
        default:
          // Opción no válida
          System.out.println("Opción no válida. Por favor, elige una opción válida.");
      }
    } while (opcion != 7);

    // Cerrar el objeto Scanner y la sesión de Hibernate
    sc.close();
    factory.close();
  }

  // Método para mostrar el menú de opciones
  private static void mostrarMenu() {
    System.out.println("--------------------------------------------------");
    System.out.println("                  BIBLIOTECA");
    System.out.println("--------------------------------------------------");
    System.out.println("1 - Insertar Libro");
    System.out.println("2 - Insertar Lector");
    System.out.println("3 - Listado de Libros");
    System.out.println("4 - Listado de Lectores");
    System.out.println("5 - Ver Libro por ID");
    System.out.println("6 - Ver Lector por ID");
    System.out.println("7 - Salir");
    System.out.println("--------------------------------------------------");
    System.out.print("Seleccione una opción: ");
  }

  // Método para insertar un nuevo libro en la base de datos
  private static void insertarLibro() {
    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    // Iniciar una nueva transacción
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      // Crear un nuevo objeto Libro con datos de ejemplo
      Libro libro = new Libro("El señor de los anillos", "J.R.R. Tolkien", 1954, true);
      // Guardar el libro en la base de datos
      session.save(libro);
      // Confirmar la transacción
      tx.commit();
      System.out.println("Libro insertado con éxito.");
    } catch (Exception e) {
      // En caso de error, hacer rollback de la transacción
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      // Cerrar la sesión de Hibernate
      session.close();
    }
  }

  // Método para insertar un nuevo lector en la base de datos
  private static void insertarLector() {
    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    // Iniciar una nueva transacción
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      // Crear un nuevo objeto Lector con datos de ejemplo
      Lector lector = new Lector("Juan", "Pérez", "juan@example.com");
      // Guardar el lector en la base de datos
      session.save(lector);
      // Confirmar la transacción
      tx.commit();
      System.out.println("Lector insertado con éxito.");
    } catch (Exception e) {
      // En caso de error, hacer rollback de la transacción
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      // Cerrar la sesión de Hibernate
      session.close();
    }
  }

  // Método para listar todos los libros almacenados en la base de datos
  private static void listarLibros() {
    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    // Consultar todos los libros y almacenarlos en una lista
    List<Libro> libros = session.createQuery("FROM Libro", Libro.class).list();
    // Mostrar los detalles de cada libro en la lista
    System.out.println("Listado de libros:");
    for (Libro libro : libros) {
      System.out.println(libro.getId() + " - " + libro.getTitulo() + " by " + libro.getAutor());
    }
    // Cerrar la sesión de Hibernate
    session.close();
  }

  // Método para listar todos los lectores almacenados en la base de datos
  private static void listarLectores() {
    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    // Consultar todos los lectores y almacenarlos en una lista
    List<Lector> lectores = session.createQuery("FROM Lector", Lector.class).list();
    // Mostrar los detalles de cada lector en la lista
    System.out.println("Listado de lectores:");
    for (Lector lector : lectores) {
      System.out.println(lector.getId() + " - " + lector.getNombre() + " " + lector.getApellido());
    }
    // Cerrar la sesión de Hibernate
    session.close();
  }

  // Método para ver detalles de un libro por su ID
  private static void verLibroPorID(Scanner sc) {
    try {
      // Solicitar al usuario que ingrese el ID del libro
      System.out.print("Ingrese el ID del libro: ");
      int idLibro = sc.nextInt();

      // Abrir una nueva sesión de Hibernate
      Session session = factory.openSession();
      // Obtener el libro por su ID
      Libro libro = session.get(Libro.class, idLibro);
      // Verificar si se encontró el libro y mostrar sus detalles
      if (libro != null) {
        System.out.println("Libro encontrado:");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Año de publicación: " + libro.getAnioPublicacion());
        System.out.println("Disponible: " + (libro.isDisponible() ? "Sí" : "No"));
      } else {
        System.out.println("No se encontró ningún libro con el ID proporcionado.");
      }
      // Cerrar la sesión de Hibernate
      session.close();
    } finally {
      // Cerrar el objeto Scanner
      sc.nextLine(); // Limpiar el buffer del Scanner
    }
  }

  // Método para ver detalles de un lector por su ID
  private static void verLectorPorID(Scanner sc) {
    try {
      // Solicitar al usuario que ingrese el ID del lector
      System.out.print("Ingrese el ID del lector: ");
      int idLector = sc.nextInt();

      // Abrir una nueva sesión de Hibernate
      Session session = factory.openSession();
      // Obtener el lector por su ID
      Lector lector = session.get(Lector.class, idLector);
      // Verificar si se encontró el lector y mostrar sus detalles
      if (lector != null) {
        System.out.println("Lector encontrado:");
        System.out.println("Nombre: " + lector.getNombre());
        System.out.println("Apellido: " + lector.getApellido());
        System.out.println("Email: " + lector.getEmail());
      } else {
        System.out.println("No se encontró ningún lector con el ID proporcionado.");
      }
      // Cerrar la sesión de Hibernate
      session.close();
    } finally {
      // Cerrar el objeto Scanner
      sc.nextLine(); // Limpiar el buffer del Scanner
    }
  }

  // Método para salir del programa
  private static void salir() {
    System.out.println("¡Gracias por utilizar la aplicación!");
  }
}
