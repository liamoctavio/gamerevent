package gameeventogroup.gamerevent.exception;

public class UsuarioBloqueadoException extends RuntimeException {
    public UsuarioBloqueadoException(String username) {
        super("Demasiados intentos fallidos, usuario '" + username + "' bloqueado temporalmente.");
    }
}
