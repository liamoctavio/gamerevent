package gameeventogroup.gamerevent.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gameeventogroup.gamerevent.models.Evento;
import gameeventogroup.gamerevent.repositories.EventoRepository;  
@Controller
public class InicioController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        // Puedes agregar datos al modelo si es necesario
        // model.addAttribute("mensaje", "Bienvenido!");
        List<Evento> eventos = (List<Evento>) eventoRepository.findAll();
        model.addAttribute("eventos", eventos);
        return "inicio"; // Nombre del archivo HTML (sin extensión)
    }

    
}
