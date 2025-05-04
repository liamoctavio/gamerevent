package gameeventogroup.gamerevent.controller;

import gameeventogroup.gamerevent.models.Juego;
import gameeventogroup.gamerevent.models.Usuario;
import gameeventogroup.gamerevent.repositories.JuegoRepository;
import gameeventogroup.gamerevent.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class PerfilControllerTest {

    private UsuarioRepository usuarioRepository;
    private JuegoRepository juegoRepository;
    private PerfilController perfilController;
    private Principal principal;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        juegoRepository = mock(JuegoRepository.class);
        principal = mock(Principal.class);
        perfilController = new PerfilController(usuarioRepository, juegoRepository);
    }

    @Test
    void testVerPerfil() {
        Usuario usuario = new Usuario();
        List<Juego> juegos = Arrays.asList(new Juego(), new Juego());

        when(principal.getName()).thenReturn("gamer123");
        when(usuarioRepository.findByNombreUsuario("gamer123")).thenReturn(usuario);
        when(juegoRepository.findAll()).thenReturn(juegos);

        Model model = mock(Model.class);

        String view = perfilController.verPerfil(model, principal);

        verify(model).addAttribute("usuario", usuario);
        verify(model).addAttribute("juegos", juegos);
        assertEquals("perfil", view);
    }

    @Test
    void testActualizarPerfilConJuegos() {
        Usuario usuario = new Usuario();
        Juego juego1 = mock(Juego.class);
        Juego juego2 = mock(Juego.class);

        when(principal.getName()).thenReturn("gamer123");
        when(usuarioRepository.findByNombreUsuario("gamer123")).thenReturn(usuario);
        when(juegoRepository.findById(1L)).thenReturn(Optional.of(juego1));
        when(juegoRepository.findById(2L)).thenReturn(Optional.of(juego2));

        List<Long> juegosSeleccionados = Arrays.asList(1L, 2L);

        String view = perfilController.actualizarPerfil("nuevoAvatar", true, juegosSeleccionados, principal);

        assertEquals("nuevoAvatar", usuario.getAvatar());
        assertEquals(true, usuario.getNotificaciones());
        assertEquals(2, usuario.getJuegosDeInteres().size());

        verify(usuarioRepository).save(usuario);
        assertEquals("redirect:/perfil", view);
    }

    @Test
    void testActualizarPerfilSinJuegos() {
        Usuario usuario = new Usuario();

        when(principal.getName()).thenReturn("gamer123");
        when(usuarioRepository.findByNombreUsuario("gamer123")).thenReturn(usuario);

        String view = perfilController.actualizarPerfil("otroAvatar", false, null, principal);

        assertEquals("otroAvatar", usuario.getAvatar());
        assertFalse(usuario.getNotificaciones());
        assertEquals(0, usuario.getJuegosDeInteres().size());

        verify(usuarioRepository).save(usuario);
        assertEquals("redirect:/perfil", view);
    }
}
