package gameeventogroup.gamerevent.controller;



import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gameeventogroup.gamerevent.models.Evento;
import gameeventogroup.gamerevent.repositories.EventoRepository;  
@Controller
public class InicioController {

    private final EventoRepository eventoRepository;

    public InicioController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/")
    public String redirigirInicio() {
        return "redirect:/inicio"; // Redirigir a /inicio
    }

    @GetMapping("/inicio")
    public String buscarEventos(
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "ciudad", required = false) String ciudad,
            @RequestParam(value = "fecha", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            Model model) {

        List<Evento> eventos;

        if (titulo != null && !titulo.isEmpty()) {
            // Buscar por título
            eventos = eventoRepository.findByTituloContainingIgnoreCase(titulo);
        } else if (categoria != null && !categoria.isEmpty()) {
            // Buscar por categoría
            eventos = eventoRepository.findByCategoriaContainingIgnoreCase(categoria);
        } else if (ciudad != null && !ciudad.isEmpty()) {
            // Buscar por ciudad
            eventos = eventoRepository.findByCiudadContainingIgnoreCase(ciudad);
        } else if (fecha != null) {
            // Buscar por fecha
            eventos = eventoRepository.findByFecha(fecha);
        } else {
            // Si no hay filtros, mostrar todos los eventos
            eventos = eventoRepository.findAll();
        }

        // Agregar los resultados y los filtros al modelo
        model.addAttribute("eventos", eventos);
        model.addAttribute("titulo", titulo);
        model.addAttribute("categoria", categoria);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("fecha", fecha);

        return "inicio"; // Nombre del archivo HTML (sin extensión)
    }
    
}
