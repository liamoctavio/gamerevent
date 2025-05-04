package gameeventogroup.gamerevent.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

public class UsuarioTest {

    @Test
    public void testGettersAndSetters() {
        Usuario usuario = new Usuario();

        Long id = 1L;
        String nombre = "Carlos Pérez";
        String correo = "carlos@example.com";
        String password = "password123";
        String nombreUsuario = "cperez";
        String avatar = "avatar.png";
        Boolean notificaciones = true;

        Juego juego = new Juego(); 

        usuario.setIdUsuario(id);
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setAvatar(avatar);
        usuario.setNotificaciones(notificaciones);
        usuario.setJuegosDeInteres(Set.of(juego));

        assertEquals(id, usuario.getIdUsuario());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(correo, usuario.getCorreo());
        assertEquals(password, usuario.getPassword());
        assertEquals(nombreUsuario, usuario.getNombreUsuario());
        assertEquals(avatar, usuario.getAvatar());
        assertEquals(notificaciones, usuario.getNotificaciones());
        assertEquals(1, usuario.getJuegosDeInteres().size());
        assertTrue(usuario.getJuegosDeInteres().contains(juego));
    }

    @Test
    public void testUserDetailsMethods() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("admin");
        usuario.setPassword("1234");

        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();
        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());

        assertEquals("admin", usuario.getUsername());
        assertEquals("1234", usuario.getPassword());

        assertTrue(usuario.isAccountNonExpired());
        assertTrue(usuario.isAccountNonLocked());
        assertTrue(usuario.isCredentialsNonExpired());
        assertTrue(usuario.isEnabled());
    }

}
