package gameeventogroup.gamerevent.service;

import gameeventogroup.gamerevent.models.Usuario;
import gameeventogroup.gamerevent.repositories.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyUserDetailsServiceTest {

    private UsuarioRepository usuarioRepository;
    private MyUserDetailsService myUserDetailsService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        myUserDetailsService = new MyUserDetailsService(usuarioRepository);
    }

    @Test
    void loadUserByUsername_usuarioExiste_debeRetornarUserDetails() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        usuario.setPassword("password123");

        when(usuarioRepository.findByNombreUsuario("testUser")).thenReturn(usuario);

        var result = myUserDetailsService.loadUserByUsername("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("password123", result.getPassword());
    }

    @Test
    void loadUserByUsername_usuarioNoExiste_debeLanzarExcepcion() {
        when(usuarioRepository.findByNombreUsuario("desconocido")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            myUserDetailsService.loadUserByUsername("desconocido");
        });
    }
}
