import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prestamos")
public class Prestamo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "fecha_prestamo")
  private Date fechaPrestamo;

  @Column(name = "fecha_devolucion")
  private Date fechaDevolucion;

  @ManyToOne
  @JoinColumn(name = "id_libro")
  private Libro libro;

  @ManyToOne
  @JoinColumn(name = "id_lector")
  private Lector lector;

  // Constructor(es)

  public Prestamo() {}

  public Prestamo(Date fechaPrestamo, Date fechaDevolucion, Libro libro, Lector lector) {
    this.fechaPrestamo = fechaPrestamo;
    this.fechaDevolucion = fechaDevolucion;
    this.libro = libro;
    this.lector = lector;
  }

  // Getters y Setters

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
