import java.util.List;
import java.util.Scanner;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

  private static SessionFactory factory;

  public static void main(String[] args) {
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    factory = configuration.buildSessionFactory();
    Scanner sc = new Scanner(System.in);
    int opcion;

    do {
      mostrarMenu();
      while (!sc.hasNextInt()) {
        System.out.println("Opción no válida. Por favor, elige una opción válida.");
        sc.next(); // Limpiar el buffer del Scanner
      }
      opcion = sc.nextInt();
      switch (opcion) {
        case 1:
          insertarLibro(sc);
          break;
        case 2:
          insertarLector(sc);
          break;
        case 3:
          listarLibros();
          break;
        case 4:
          listarLectores();
          break;
        case 5:
          verLibroPorID(sc);
          break;
        case 6:
          verLectorPorID(sc);
          break;
        case 7:
          gestionarPrestamos(sc);
          break;
        case 8:
          gestionarLibrosYLectores(sc);
          break;
        case 9:
          consultasBBDD(sc);
          break;
        case 10:
          salir();
          break;
        default:
          System.out.println("Opción no válida. Por favor, elige una opción válida.");
      }
    } while (opcion != 10);

    sc.close();
    factory.close();
  }

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
    System.out.println("7 - Gestion de prestamos");
    System.out.println("8 - Gestion de libros y Lectores");
    System.out.println("9 - Consultas en la BBDD");
    System.out.println("10 - Salir");
    System.out.println("--------------------------------------------------");
    System.out.print("Seleccione una opción: ");
  }

  private static void insertarLibro(Scanner sc) {
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      System.out.print("Ingrese el título del libro: ");
      sc.nextLine();
      String titulo = sc.nextLine();
      System.out.print("Ingrese el autor del libro: ");
      String autor = sc.nextLine();
      System.out.print("Ingrese el año de publicación del libro: ");
      int anioPublicacion = sc.nextInt();

      // Establecer la disponibilidad del libro como false automáticamente al insertarlo
      boolean disponible = true;

      Libro libro = new Libro(titulo, autor, anioPublicacion, disponible);
      session.save(libro);
      tx.commit();
      System.out.println("Libro insertado con éxito.");
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }


  private static void insertarLector(Scanner sc) {
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      System.out.print("Ingrese el nombre y ");
      String nombre = sc.nextLine();
      System.out.print("el apellido del lector: ");
      String apellido = sc.nextLine();

      System.out.print("Ingrese el email del lector: ");
      String email = sc.nextLine();
      Lector lector = new Lector(nombre, apellido, email);
      session.save(lector);
      tx.commit();
      System.out.println("Lector insertado con éxito.");
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void listarLibros() {
    Session session = factory.openSession();
    List<Libro> libros = session.createQuery("FROM Libro", Libro.class).list();
    System.out.println("Listado de libros:");
    for (Libro libro : libros) {
      System.out
          .println(libro.getId() + " - " + libro.getTitulo() + " escrit@ por " + libro.getAutor());
    }
    session.close();
  }

  private static void listarLectores() {
    Session session = factory.openSession();
    List<Lector> lectores = session.createQuery("FROM Lector", Lector.class).list();
    System.out.println("Listado de lectores:");
    for (Lector lector : lectores) {
      System.out.println(lector.getId() + " - " + lector.getNombre() + " " + lector.getApellido());
    }
    session.close();
  }

  private static void verLibroPorID(Scanner sc) {
    try {
      System.out.print("Ingrese el ID del libro: ");
      int idLibro = sc.nextInt();
      Session session = factory.openSession();
      Libro libro = session.get(Libro.class, idLibro);
      if (libro != null) {
        System.out.println("Libro encontrado:");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Año de publicación: " + libro.getAnioPublicacion());
        System.out.println("Disponible: " + (libro.isDisponible() ? "Sí" : "No"));
      } else {
        System.out.println("No se encontró ningún libro con el ID proporcionado.");
      }
      session.close();
    } finally {
      sc.nextLine();
    }
  }

  private static void verLectorPorID(Scanner sc) {
    try {
      System.out.print("Ingrese el ID del lector: ");
      int idLector = sc.nextInt();
      Session session = factory.openSession();
      Lector lector = session.get(Lector.class, idLector);
      if (lector != null) {
        System.out.println("Lector encontrado:");
        System.out.println("Nombre y apellidos: " + lector.getNombre() + lector.getApellido());
        System.out.println("Email: " + lector.getEmail());
      } else {
        System.out.println("No se encontró ningún lector con el ID proporcionado.");
      }
      session.close();
    } finally {
      sc.nextLine();
    }
  }

  private static void gestionarPrestamos(Scanner sc) {
    System.out.println("1 - Asignar libro a lector");
    System.out.println("2 - Registrar devolución");
    System.out.print("Seleccione una opción: ");
    int opcion = sc.nextInt();
    switch (opcion) {
      case 1:
        asignarLibroALector(sc);
        break;
      case 2:
        registrarDevolucion(sc);
        break;
      default:
        System.out.println("Opción no válida.");
    }
  }

  private static void asignarLibroALector(Scanner sc) {
    // Solicitar al usuario el ID del libro y del lector
    System.out.print("Ingrese el ID del libro: ");
    int idLibro = sc.nextInt();
    System.out.print("Ingrese el ID del lector: ");
    int idLector = sc.nextInt();

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();

    Transaction tx = null;
    try {
      tx = session.beginTransaction();

      // Obtener el libro y el lector por sus IDs
      Libro libro = session.get(Libro.class, idLibro);
      Lector lector = session.get(Lector.class, idLector);

      // Verificar si el libro y el lector existen
      if (libro != null && lector != null) {
        // Verificar si el libro está disponible
        if (libro.isDisponible()) {
          // Asignar el libro al lector
          lector.addLibro(libro);
          session.update(lector);
          libro.setDisponible(false); // Marcar el libro como no disponible
          session.update(libro); // Actualizar el estado del libro en la base de datos
          System.out.println("Libro asignado al lector correctamente.");
        } else {
          System.out.println("El libro no está disponible para préstamo.");
        }
      } else {
        System.out.println("No se encontró el libro o el lector con los IDs proporcionados.");
      }

      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void registrarDevolucion(Scanner sc) {
    // Solicitar al usuario el ID del libro devuelto
    System.out.print("Ingrese el ID del libro devuelto: ");
    int idLibro = sc.nextInt();

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Obtener el libro por su ID
      Libro libro = session.get(Libro.class, idLibro);

      // Verificar si el libro existe
      if (libro != null) {
        // Verificar si el libro estaba prestado antes de registrar la devolución
        if (!libro.isDisponible()) {
          // Marcar el libro como disponible
          libro.setDisponible(true);
          session.update(libro);
          tx.commit();
          System.out
              .println("Devolución registrada correctamente. El libro ahora está disponible.");
        } else {
          System.out.println("El libro ya estaba disponible antes de la devolución.");
        }
      } else {
        System.out.println("No se encontró el libro con el ID proporcionado.");
      }
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }


  private static void gestionarLibrosYLectores(Scanner sc) {
    System.out.println("1 - Crear libro");
    System.out.println("2 - Leer libro");
    System.out.println("3 - Actualizar libro");
    System.out.println("4 - Borrar libro");
    System.out.println("5 - Crear lector");
    System.out.println("6 - Leer lector");
    System.out.println("7 - Actualizar lector");
    System.out.println("8 - Borrar lector");
    int opcion = sc.nextInt();
    switch (opcion) {
      case 1:
        crearLibro(sc);
        break;
      case 2:
        leerLibro(sc);
        break;
      case 3:
        actualizarLibro(sc);
        break;
      case 4:
        borrarLibro(sc);
        break;
      case 5:
        crearLector(sc);
        break;
      case 6:
        leerLector(sc);
        break;
      case 7:
        actualizarLector(sc);
        break;
      case 8:
        borrarLector(sc);
        break;
      default:
        System.out.println("Opción no válida.");
    }
  }

  private static void crearLibro(Scanner sc) {
    // Solicitar al usuario los datos del libro
    System.out.print("Ingrese el título del libro: ");
    sc.nextLine(); // Limpiar el buffer del Scanner
    String titulo = sc.nextLine();
    System.out.print("Ingrese el autor del libro: ");
    String autor = sc.nextLine();
    System.out.print("Ingrese el año de publicación del libro: ");
    int anioPublicacion = sc.nextInt();
    System.out.print("¿Está disponible el libro? (true/false): ");
    boolean disponible = sc.nextBoolean();

    // Crear un nuevo objeto Libro con los datos proporcionados
    Libro libro = new Libro(titulo, autor, anioPublicacion, disponible);

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Guardar el libro en la base de datos
      session.save(libro);
      tx.commit();
      System.out.println("Libro creado correctamente.");
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void leerLibro(Scanner sc) {
    // Solicitar al usuario el ID del libro a leer
    System.out.print("Ingrese el ID del libro a leer: ");
    int idLibro = sc.nextInt();

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();

    try {
      // Obtener el libro por su ID
      Libro libro = session.get(Libro.class, idLibro);

      // Verificar si el libro existe
      if (libro != null) {
        // Mostrar los detalles del libro
        System.out.println("Detalles del libro:");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Año de publicación: " + libro.getAnioPublicacion());
        System.out.println("Disponible: " + (libro.isDisponible() ? "Sí" : "No"));
      } else {
        System.out.println("No se encontró el libro con el ID proporcionado.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void actualizarLibro(Scanner sc) {
    // Solicitar al usuario el ID del libro a actualizar
    System.out.print("Ingrese el ID del libro a actualizar: ");
    int idLibro = sc.nextInt();
    sc.nextLine(); // Limpiar el buffer del Scanner

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Obtener el libro por su ID
      Libro libro = session.get(Libro.class, idLibro);

      // Verificar si el libro existe
      if (libro != null) {
        // Solicitar al usuario los nuevos datos del libro
        System.out.print("Ingrese el nuevo título del libro: ");
        String nuevoTitulo = sc.nextLine();
        System.out.print("Ingrese el nuevo autor del libro: ");
        String nuevoAutor = sc.nextLine();
        System.out.print("Ingrese el nuevo año de publicación del libro: ");
        int nuevoAnioPublicacion = sc.nextInt();
        System.out.print("¿Está disponible el libro? (true/false): ");
        boolean nuevoDisponible = sc.nextBoolean();

        // Actualizar los datos del libro
        libro.setTitulo(nuevoTitulo);
        libro.setAutor(nuevoAutor);
        libro.setAnioPublicacion(nuevoAnioPublicacion);
        libro.setDisponible(nuevoDisponible);

        // Actualizar el libro en la base de datos
        session.update(libro);
        tx.commit();
        System.out.println("Libro actualizado correctamente.");
      } else {
        System.out.println("No se encontró el libro con el ID proporcionado.");
      }
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }


  private static void borrarLibro(Scanner sc) {
    // Solicitar al usuario el ID del libro a borrar
    System.out.print("Ingrese el ID del libro a borrar: ");
    int idLibro = sc.nextInt();
    sc.nextLine(); // Limpiar el buffer del Scanner

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Eliminar las referencias del libro en la tabla lector_libro
      Query deleteReferencesQuery =
          session.createNativeQuery("DELETE FROM lector_libro WHERE libros_id = :idLibro");
      deleteReferencesQuery.setParameter("idLibro", idLibro);
      deleteReferencesQuery.executeUpdate();

      // Obtener el libro por su ID
      Libro libro = session.get(Libro.class, idLibro);

      // Verificar si el libro existe
      if (libro != null) {
        // Eliminar el libro de la base de datos
        session.delete(libro);
        tx.commit();
        System.out.println("Libro eliminado correctamente.");
      } else {
        System.out.println("No se encontró el libro con el ID proporcionado.");
      }
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }


  private static void crearLector(Scanner sc) {
    // Solicitar al usuario los datos del nuevo lector
    System.out.print("Ingrese el nombre y ");
    String nombre = sc.nextLine();
    System.out.print("el apellido del nuevo lector: ");
    String apellido = sc.nextLine();
    System.out.print("Ingrese el email del nuevo lector: ");
    String email = sc.nextLine();

    // Crear un objeto Lector con los datos proporcionados
    Lector lector = new Lector(nombre, apellido, email);

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Guardar el nuevo lector en la base de datos
      session.save(lector);
      tx.commit();
      System.out.println("Lector creado correctamente.");
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void leerLector(Scanner sc) {
    // Solicitar al usuario el ID del lector a leer
    System.out.print("Ingrese el ID del lector a leer: ");
    int idLector = sc.nextInt();

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();

    try {
      // Obtener el lector por su ID
      Lector lector = session.get(Lector.class, idLector);

      // Verificar si el lector existe
      if (lector != null) {
        // Mostrar los detalles del lector
        System.out.println("Detalles del lector:");
        System.out.println("Nombre: " + lector.getNombre());
        System.out.println("Apellido: " + lector.getApellido());
        System.out.println("Email: " + lector.getEmail());
      } else {
        System.out.println("No se encontró el lector con el ID proporcionado.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void actualizarLector(Scanner sc) {
    // Solicitar al usuario el ID del lector a actualizar
    System.out.print("Ingrese el ID del lector a actualizar: ");
    int idLector = sc.nextInt();
    sc.nextLine(); // Limpiar el buffer del Scanner

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Obtener el lector por su ID
      Lector lector = session.get(Lector.class, idLector);

      // Verificar si el lector existe
      if (lector != null) {
        // Solicitar al usuario los nuevos datos del lector
        System.out.print("Ingrese el nuevo nombre del lector: ");
        String nuevoNombre = sc.nextLine();
        System.out.print("Ingrese el nuevo apellido del lector: ");
        String nuevoApellido = sc.nextLine();
        System.out.print("Ingrese el nuevo email del lector: ");
        String nuevoEmail = sc.nextLine();

        // Actualizar los datos del lector
        lector.setNombre(nuevoNombre);
        lector.setApellido(nuevoApellido);
        lector.setEmail(nuevoEmail);

        // Actualizar el lector en la base de datos
        session.update(lector);
        tx.commit();
        System.out.println("Lector actualizado correctamente.");
      } else {
        System.out.println("No se encontró el lector con el ID proporcionado.");
      }
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void borrarLector(Scanner sc) {
    // Solicitar al usuario el ID del lector a borrar
    System.out.print("Ingrese el ID del lector a borrar: ");
    int idLector = sc.nextInt();
    sc.nextLine(); // Limpiar el buffer del Scanner

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Obtener el lector por su ID
      Lector lector = session.get(Lector.class, idLector);

      // Verificar si el lector existe
      if (lector != null) {
        // Eliminar el lector de la base de datos
        session.delete(lector);
        tx.commit();
        System.out.println("Lector eliminado correctamente.");
      } else {
        System.out.println("No se encontró el lector con el ID proporcionado.");
      }
    } catch (Exception e) {
      if (tx != null)
        tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  private static void consultasBBDD(Scanner sc) {
    System.out.println("1 - Libros actualmente prestados a un lector");
    System.out.println("2 - Libros disponibles para préstamo");
    System.out.println("3 - Historial de préstamos por lector");
    int opcion = sc.nextInt();
    switch (opcion) {
      case 1:
        librosPrestadosAUnLector(sc);
        break;
      case 2:
        librosDisponiblesParaPrestamo();
        break;
      case 3:
        historialPrestamosPorLector(sc);
        break;
      default:
        System.out.println("Opción no válida.");
    }
  }

  @SuppressWarnings("unchecked")
  private static void librosPrestadosAUnLector(Scanner sc) {
    // Solicitar al usuario el ID del lector
    System.out.print("Ingrese el ID del lector: ");
    int idLector = sc.nextInt();

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();

    try {
      // Obtener el lector por su ID
      Lector lector = session.get(Lector.class, idLector);

      // Verificar si el lector existe
      if (lector != null) {
        // Crear una consulta para obtener los libros prestados al lector que no están disponibles
        // para otros lectores
        Query query = session.createQuery(
            "SELECT l FROM Libro l JOIN l.lectores lec WHERE lec.id = :lectorId AND l.disponible = false");
        query.setParameter("lectorId", idLector);

        // Obtener la lista de libros prestados al lector y realizar un cast seguro
        List<Libro> librosPrestados = (List<Libro>) query.getResultList();

        // Mostrar los libros prestados al lector que no están disponibles para otros lectores
        System.out.println("Libros que posee actualmente el lector:");
        if (librosPrestados.isEmpty()) {
          System.out.println("El lector no tiene libros prestados actualmente.");
        } else {
          for (Libro libro : librosPrestados) {
            System.out.println("Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
          }
        }
      } else {
        System.out.println("No se encontró el lector con el ID proporcionado.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  @SuppressWarnings("unchecked")
  private static void librosDisponiblesParaPrestamo() {
    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();

    try {
      // Consultar todos los libros disponibles para préstamo
      String hql = "FROM Libro WHERE disponible = true";
      Query query = session.createQuery(hql);
      List<Libro> librosDisponibles = query.getResultList();

      // Imprimir la lista de libros disponibles
      System.out.println("Libros disponibles para préstamo:");
      for (Libro libro : librosDisponibles) {
        System.out.println("Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  @SuppressWarnings("unchecked")
  private static void historialPrestamosPorLector(Scanner sc) {
    // Solicitar al usuario el ID del lector
    System.out.print("Ingrese el ID del lector: ");
    int idLector = sc.nextInt();

    // Abrir una nueva sesión de Hibernate
    Session session = factory.openSession();

    try {
      // Obtener el lector por su ID
      Lector lector = session.get(Lector.class, idLector);

      // Verificar si el lector existe
      if (lector != null) {
        // Crear una consulta para obtener los libros prestados al lector
        Query query = session
            .createQuery("SELECT l FROM Libro l JOIN l.lectores lec WHERE lec.id = :lectorId");
        query.setParameter("lectorId", idLector);

        // Obtener la lista de libros prestados al lector y realizar un cast seguro
        List<Libro> librosPrestados = (List<Libro>) query.getResultList();

        // Mostrar el historial de los libros prestados al lector
        System.out.println("Historial de libros prestados al lector:");
        for (Libro libro : librosPrestados) {
          System.out.println("Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
        }
      } else {
        System.out.println("No se encontró el lector con el ID proporcionado.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }
  }



  private static void salir() {
    System.out.println("<---TERMINADO--->");
  }
}
