package gameeventogroup.gamerevent.models;

import jakarta.persistence.*;

@Entity
public class Expositor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExpositor;

    private String nombre;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    public Long getIdExpositor() {
        return idExpositor;
    }

    public void setIdExpositor(Long idExpositor) {
        this.idExpositor = idExpositor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    // getters y setters

    
}

