package gameeventogroup.gamerevent.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;

    @Column(unique = true)
    private String correo;

    private String password;

    @Column(unique = true)
    private String nombreUsuario;

    private String avatar;

    private Boolean notificaciones;

    @ManyToMany
    @JoinTable(
        name = "usuario_juego",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_juego")
    )
    private Set<Juego> juegosDeInteres = new HashSet<>();

    // Implementación mínima de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(); // No tienes roles definidos aún
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // getters y setters normales
}
