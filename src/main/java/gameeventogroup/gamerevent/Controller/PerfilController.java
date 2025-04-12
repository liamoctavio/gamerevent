package gameeventogroup.gamerevent.controller;

import gameeventogroup.gamerevent.models.Juego;
import gameeventogroup.gamerevent.models.Usuario;
import gameeventogroup.gamerevent.repositories.JuegoRepository;
import gameeventogroup.gamerevent.repositories.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    private final UsuarioRepository usuarioRepository;
    private final JuegoRepository juegoRepository;

    public PerfilController(UsuarioRepository usuarioRepository, JuegoRepository juegoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.juegoRepository = juegoRepository;
    }

    @GetMapping
    public String verPerfil(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(principal.getName());
        List<Juego> juegos = juegoRepository.findAll();

        model.addAttribute("usuario", usuario);
        model.addAttribute("juegos", juegos);
        return "perfil";
    }

    @PostMapping("/actualizar")
    public String actualizarPerfil(
        @RequestParam("avatar") String avatar,
        @RequestParam("notificaciones") boolean notificaciones,
        @RequestParam(value = "juegosSeleccionados", required = false) List<Long> juegosSeleccionados,
        Principal principal
    ) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(principal.getName());

        usuario.setAvatar(avatar);
        usuario.setNotificaciones(notificaciones);

        // Actualizar juegos
        Set<Juego> juegos = new HashSet<>();
        if (juegosSeleccionados != null) {
            juegosSeleccionados.forEach(id -> juegoRepository.findById(id).ifPresent(juegos::add));
        }
        usuario.setJuegosDeInteres(juegos);

        usuarioRepository.save(usuario);

        return "redirect:/perfil";
    }
}
