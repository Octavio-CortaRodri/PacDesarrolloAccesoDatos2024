import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String titulo;
  private String autor;
  private int anioPublicacion;
  private boolean disponible;

  @ManyToMany(mappedBy = "libros")
  private List<Lector> lectores = new ArrayList<>();

  @OneToMany(mappedBy = "libro")
  private List<Prestamo> prestamos = new ArrayList<>();

  public Libro() {}

  public Libro(String titulo, String autor, int anioPublicacion, boolean disponible) {
    this.titulo = titulo;
    this.autor = autor;
    this.anioPublicacion = anioPublicacion;
    this.disponible = disponible;
  }

  // Getters y setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public int getAnioPublicacion() {
    return anioPublicacion;
  }

  public void setAnioPublicacion(int anioPublicacion) {
    this.anioPublicacion = anioPublicacion;
  }

  public boolean isDisponible() {
    return disponible;
  }

  public void setDisponible(boolean disponible) {
    this.disponible = disponible;
  }

  public List<Lector> getLectores() {
    return lectores;
  }

  public void setLectores(List<Lector> lectores) {
    this.lectores = lectores;
  }

  public List<Prestamo> getPrestamos() {
    return prestamos;
  }

  public void setPrestamos(List<Prestamo> prestamos) {
    this.prestamos = prestamos;
  }
}
