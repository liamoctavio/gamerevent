package gameeventogroup.gamerevent.controller;

import gameeventogroup.gamerevent.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistroControllerTest {

    private UsuarioService usuarioService;
    private RegistroController registroController;

    @BeforeEach
    void setUp() {
        usuarioService = mock(UsuarioService.class);
        registroController = new RegistroController(usuarioService);
    }

    @Test
    void testMostrarRegistroDevuelveVistaCorrecta() {
        String vista = registroController.mostrarRegistro();
        assertEquals("registro", vista);
    }

    @Test
    void testRegistrarUsuarioConUsernameExistente() {
        when(usuarioService.existeUsuario("gamer1")).thenReturn(true);

        Model model = new ConcurrentModel();
        String vista = registroController.registrarUsuario("gamer1", "gamer@email.com", "1234", model);

        assertEquals("registro", vista);
        assertTrue(model.containsAttribute("errorUsername"));
    }

    @Test
    void testRegistrarUsuarioConEmailExistente() {
        when(usuarioService.existeUsuario("gamer2")).thenReturn(false);
        when(usuarioService.existeEmail("gamer@email.com")).thenReturn(true);

        Model model = new ConcurrentModel();
        String vista = registroController.registrarUsuario("gamer2", "gamer@email.com", "1234", model);

        assertEquals("registro", vista);
        assertTrue(model.containsAttribute("errorEmail"));
    }

    @Test
    void testRegistrarUsuarioCorrectamente() {
        when(usuarioService.existeUsuario("gamer3")).thenReturn(false);
        when(usuarioService.existeEmail("gamer3@email.com")).thenReturn(false);

        Model model = new ConcurrentModel();
        String vista = registroController.registrarUsuario("gamer3", "gamer3@email.com", "secure123", model);

        verify(usuarioService).crearUsuario("gamer3", "gamer3@email.com", "secure123");
        assertEquals("redirect:/login?registroExitoso", vista);
    }
}
