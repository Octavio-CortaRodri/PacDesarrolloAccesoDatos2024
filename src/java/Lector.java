import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nombre;
  private String apellido;
  private String email;

  @ManyToMany
  private List<Libro> libros;

  public Lector() {
    libros = new ArrayList<>();
  }

  public Lector(String nombre, String apellido, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    libros = new ArrayList<>();
  }

  // Getters y setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Libro> getLibros() {
    return libros;
  }

  public void setLibros(List<Libro> libros) {
    this.libros = libros;
  }

  public void addLibro(Libro libro) {
    // Verificar si el libro ya está en la lista antes de agregarlo
    if (!libros.contains(libro)) {
      libros.add(libro);
      libro.getLectores().add(this);
    }
  }


  public void removeLibro(Libro libro) {
    libros.remove(libro);
    libro.getLectores().remove(this);
  }
}
