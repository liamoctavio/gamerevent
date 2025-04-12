package gameeventogroup.gamerevent.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJuego;

    private String nombre;

    @ManyToMany(mappedBy = "juegosDeInteres")
    private Set<Usuario> usuarios;

    public Long getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Long idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    // getters y setters

    
}
