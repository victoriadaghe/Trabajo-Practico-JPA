package entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "numero")
    private int numero;

    @Column(name = "total")
    private int total;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

 //   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<DetalleFactura> detalles= new ArrayList<DetalleFactura>();

       @OneToMany( mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<DetalleFactura> detalles= new ArrayList<DetalleFactura>();



    // Constructor por defecto
    public Factura() {}

    // Constructor con parámetros
    public Factura(String fecha, int numero, int total) {
        this.fecha = fecha;
        this.numero = numero;
        this.total = total;
    }

    public Factura(String fecha, int numero, int total, Cliente cliente) {
        this.fecha = fecha;
        this.numero = numero;
        this.total = total;
        this.cliente = cliente;}

    // Getters y Setters


    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
