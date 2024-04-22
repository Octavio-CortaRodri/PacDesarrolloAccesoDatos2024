import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Prestamo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Date fechaPrestamo;
  private Date fechaDevolucion;

  @ManyToOne
  @JoinColumn(name = "libro_id")
  private Libro libro;

  @ManyToOne
  @JoinColumn(name = "lector_id")
  private Lector lector;

  public Prestamo() {}

  public Prestamo(Date fechaPrestamo, Date fechaDevolucion, Libro libro, Lector lector) {
    this.fechaPrestamo = fechaPrestamo;
    this.fechaDevolucion = fechaDevolucion;
    this.libro = libro;
    this.lector = lector;
  }

  // Getters y setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getFechaPrestamo() {
    return fechaPrestamo;
  }

  public void setFechaPrestamo(Date fechaPrestamo) {
    this.fechaPrestamo = fechaPrestamo;
  }

  public Date getFechaDevolucion() {
    return fechaDevolucion;
  }

  public void setFechaDevolucion(Date fechaDevolucion) {
    this.fechaDevolucion = fechaDevolucion;
  }

  public Libro getLibro() {
    return libro;
  }

  public void setLibro(Libro libro) {
    this.libro = libro;
  }

  public Lector getLector() {
    return lector;
  }

  public void setLector(Lector lector) {
    this.lector = lector;
  }
}
