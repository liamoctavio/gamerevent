package gameeventogroup.gamerevent.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioBloqueadoExceptionTest {

    @Test
    void testMensajeDeExcepcion() {
        String username = "testUser";
        UsuarioBloqueadoException exception = new UsuarioBloqueadoException(username);

        assertEquals("Demasiados intentos fallidos, usuario 'testUser' bloqueado temporalmente.", exception.getMessage());
    }
}
