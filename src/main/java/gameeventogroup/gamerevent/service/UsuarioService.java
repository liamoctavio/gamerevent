package gameeventogroup.gamerevent.service;

import gameeventogroup.gamerevent.models.Usuario;
import gameeventogroup.gamerevent.repositories.UsuarioRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existeUsuario(String username) {
        return usuarioRepository.findByNombreUsuario(username) != null;
    }

    public boolean existeEmail(String email) {
        return usuarioRepository.findByCorreo(email) != null;
    }

    public void crearUsuario(String username, String email, String password) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(username);
        nuevoUsuario.setCorreo(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        usuarioRepository.save(nuevoUsuario);
    }

     

    private Map<String, Integer> intentos = new ConcurrentHashMap<>();

    public void registrarIntento(String username) {
        intentos.put(username, intentos.getOrDefault(username, 0) + 1);
        if (intentos.get(username) > 5) {
            throw new RuntimeException("Demasiados intentos fallidos, usuario bloqueado temporalmente");
        }
    }

    public void limpiarIntentos(String username) {
        intentos.remove(username);
    }
    


}
