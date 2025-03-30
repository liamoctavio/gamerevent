package gameeventogroup.gamerevent.controller;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import gameeventogroup.gamerevent.models.Evento;
import gameeventogroup.gamerevent.repositories.EventoRepository;  
@Controller
public class InicioController {

    @Autowired
    private EventoRepository eventoRepository;

    public InicioController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    // @GetMapping("/inicio")
    // public String mostrarInicio(Model model) {
    //     // Puedes agregar datos al modelo si es necesario
    //     // model.addAttribute("mensaje", "Bienvenido!");
    //     List<Evento> eventos = (List<Evento>) eventoRepository.findAll();
    //     model.addAttribute("eventos", eventos);
    //     return "inicio"; // Nombre del archivo HTML (sin extensión)
    // }

    // @GetMapping("/inicio")
    // public String mostrarInicio(@RequestParam(value = "q", required = false) String query, Model model) {
    //     List<Evento> eventos;
    //     if (query != null && !query.isEmpty()) {
    //         // Filtrar eventos por el nombre que contenga el texto de búsqueda
    //         eventos = eventoRepository.findByTituloContainingIgnoreCase(query);
    //     } else {
    //         // Si no hay búsqueda, mostrar todos los eventos
    //         eventos = (List<Evento>) eventoRepository.findAll();
    //     }
    //     model.addAttribute("eventos", eventos);
    //     model.addAttribute("query", query); // Para mantener el texto de búsqueda en el formulario
    //     return "inicio"; // Nombre del archivo HTML (sin extensión)
    // }

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



    // @GetMapping("/inicio")
    // public String buscarEventos(
    //         @RequestParam(value = "categoria", required = false) String categoria,
    //         @RequestParam(value = "ciudad", required = false) String ciudad,
    //         @RequestParam(value = "fecha", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
    //         Model model) {

    //     List<Evento> eventos = eventoRepository.buscarPorFiltros(categoria, ciudad, fecha);

    //     model.addAttribute("eventos", eventos);
    //     model.addAttribute("categoria", categoria);
    //     model.addAttribute("ciudad", ciudad);
    //     model.addAttribute("fecha", fecha);

    //     return "inicio";
    // }


    
}
