package gameeventogroup.gamerevent.controller;

import gameeventogroup.gamerevent.models.Evento;
import gameeventogroup.gamerevent.repositories.EventoRepository;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }


    @GetMapping
    public String listarEventos(Model model) {
        List<Evento> eventos = eventoRepository.findAll();
        model.addAttribute("eventos", eventos);
        return "eventos";
    }

    @Transactional
    @GetMapping("/{id}")
    public String detalleEvento(@PathVariable("id") Long id, Model model) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento == null) {
            return "redirect:/eventos";
        }
        model.addAttribute("evento", evento);
        return "eventoDetalle";
    }
}
