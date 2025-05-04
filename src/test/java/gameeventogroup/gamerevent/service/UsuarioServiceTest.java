package gameeventogroup.gamerevent.service;

import gameeventogroup.gamerevent.exception.UsuarioBloqueadoException;
import gameeventogroup.gamerevent.models.Usuario;
import gameeventogroup.gamerevent.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        usuarioService = new UsuarioService(usuarioRepository, passwordEncoder);
    }

    @Test
    void testExisteUsuario_True() {
        when(usuarioRepository.findByNombreUsuario("gamer")).thenReturn(new Usuario());
        assertTrue(usuarioService.existeUsuario("gamer"));
    }

    @Test
    void testExisteUsuario_False() {
        when(usuarioRepository.findByNombreUsuario("nuevo")).thenReturn(null);
        assertFalse(usuarioService.existeUsuario("nuevo"));
    }

    @Test
    void testExisteEmail_True() {
        when(usuarioRepository.findByCorreo("mail@example.com")).thenReturn(new Usuario());
        assertTrue(usuarioService.existeEmail("mail@example.com"));
    }

    @Test
    void testExisteEmail_False() {
        when(usuarioRepository.findByCorreo("nuevo@mail.com")).thenReturn(null);
        assertFalse(usuarioService.existeEmail("nuevo@mail.com"));
    }

    @Test
    void testCrearUsuario() {
        when(passwordEncoder.encode("pass123")).thenReturn("encryptedPass");

        usuarioService.crearUsuario("nuevo", "nuevo@mail.com", "pass123");

        verify(usuarioRepository, times(1)).save(Mockito.argThat(usuario -> 
            usuario.getNombreUsuario().equals("nuevo") &&
            usuario.getCorreo().equals("nuevo@mail.com") &&
            usuario.getPassword().equals("encryptedPass")
        ));
    }

    @Test
    void testRegistrarIntento_NoExcedeLimite() {
        for (int i = 0; i < 5; i++) {
            assertDoesNotThrow(() -> usuarioService.registrarIntento("usuario"));
        }
    }

    @Test
    void testRegistrarIntento_ExcedeLimite() {
        for (int i = 0; i < 5; i++) {
            usuarioService.registrarIntento("bloqueado");
        }

        assertThrows(UsuarioBloqueadoException.class, () -> {
            usuarioService.registrarIntento("bloqueado");
        });
    }

    @Test
    void testLimpiarIntentos() {
        usuarioService.registrarIntento("paraBorrar");
        usuarioService.limpiarIntentos("paraBorrar");

        for (int i = 0; i < 5; i++) {
            assertDoesNotThrow(() -> usuarioService.registrarIntento("paraBorrar"));
        }
    }
}
