package gameeventogroup.gamerevent.models;

import jakarta.persistence.*;

@Entity
public class ServicioEvento {

    @Id
    private Long idEvento;

    private Boolean estacionamiento;
    private Boolean locomocion;
    private Boolean comidas;
    private Boolean alojamiento;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_evento")
    private Evento evento;

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public Boolean getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Boolean estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Boolean getLocomocion() {
        return locomocion;
    }

    public void setLocomocion(Boolean locomocion) {
        this.locomocion = locomocion;
    }

    public Boolean getComidas() {
        return comidas;
    }

    public void setComidas(Boolean comidas) {
        this.comidas = comidas;
    }

    public Boolean getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Boolean alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    // getters y setters

    
}

