package gameeventogroup.gamerevent.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String direccion;

    private LocalDate fecha;

    private LocalTime hora;

    private String categoria;

    private String ciudad;

    private String pais;

    @ManyToMany
    @JoinTable(
        name = "evento_organizador",
        joinColumns = @JoinColumn(name = "id_evento"),
        inverseJoinColumns = @JoinColumn(name = "id_organizador")
    )
    private Set<Organizador> organizadores = new HashSet<>();

    @OneToOne(mappedBy = "evento", cascade = CascadeType.ALL)
    private ServicioEvento servicios;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private Set<Expositor> expositores = new HashSet<>();

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Set<Organizador> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(Set<Organizador> organizadores) {
        this.organizadores = organizadores;
    }

    public ServicioEvento getServicios() {
        return servicios;
    }

    public void setServicios(ServicioEvento servicios) {
        this.servicios = servicios;
    }

    public Set<Expositor> getExpositores() {
        return expositores;
    }

    public void setExpositores(Set<Expositor> expositores) {
        this.expositores = expositores;
    }

    // getters y setters

    
}

