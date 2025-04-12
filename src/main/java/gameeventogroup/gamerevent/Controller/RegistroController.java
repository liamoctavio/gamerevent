package gameeventogroup.gamerevent.controller;

import gameeventogroup.gamerevent.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {


    private final UsuarioService usuarioService;
    private static final String REGISTRO_VIEW = "registro";

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return REGISTRO_VIEW;
    }

    @PostMapping("/registrar")
    public String registrarUsuario(
        @RequestParam String username,
        @RequestParam String email,
        @RequestParam String password,
        Model model
    ) {
        if (usuarioService.existeUsuario(username)) {
            model.addAttribute("errorUsername", true);
            return REGISTRO_VIEW;
        }
        
        if (usuarioService.existeEmail(email)) {
            model.addAttribute("errorEmail", true);
            return REGISTRO_VIEW;
        }
        
        usuarioService.crearUsuario(username, email, password);
        return "redirect:/login?registroExitoso";
    }
}
