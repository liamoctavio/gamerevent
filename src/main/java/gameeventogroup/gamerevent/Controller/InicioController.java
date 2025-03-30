package gameeventogroup.gamerevent.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        // Puedes agregar datos al modelo si es necesario
        // model.addAttribute("mensaje", "Bienvenido!");
        return "inicio"; // Nombre del archivo HTML (sin extensión)
    }
}
