package gameeventogroup.gamerevent.controller;

import gameeventogroup.gamerevent.models.Evento;
import gameeventogroup.gamerevent.repositories.EventoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(EventoController.class)
public class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoRepository eventoRepository;

    @Test
    void listarEventos_deberiaRetornarVistaConEventos() throws Exception {
        Evento evento1 = new Evento();
        evento1.setTitulo("Conferencia A");

        Evento evento2 = new Evento();
        evento2.setTitulo("Torneo B");

        when(eventoRepository.findAll()).thenReturn(Arrays.asList(evento1, evento2));

        mockMvc.perform(get("/eventos").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("eventos"))
                .andExpect(model().attributeExists("eventos"));
    }

    @Test
    void detalleEvento_eventoExistente_deberiaRetornarVistaConEvento() throws Exception {
        Evento evento = new Evento();
        evento.setTitulo("Conferencia A");

        Field field = Evento.class.getDeclaredField("idEvento");
        field.setAccessible(true);
        field.set(evento, 1L);

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        mockMvc.perform(get("/eventos/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("eventoDetalle"))
                .andExpect(model().attributeExists("evento"));
    }

    @Test
    void detalleEvento_eventoNoExiste_redirigeAEventos() throws Exception {
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/eventos/99").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/eventos"));
    }

}
