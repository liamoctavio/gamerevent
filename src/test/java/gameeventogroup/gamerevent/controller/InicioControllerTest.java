package gameeventogroup.gamerevent.controller;

import gameeventogroup.gamerevent.repositories.EventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(InicioController.class)
class InicioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventoRepository eventoRepository;

    @Test
    void redirigirInicio_debeRedirigirCorrectamente() throws Exception {
        mockMvc.perform(get("/").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/inicio"));
    }

    @WithMockUser(username = "usuarioTest", roles = "USER")
    @Test
    void buscarEventos_sinFiltros_debeMostrarTodos() throws Exception {
        mockMvc.perform(get("/").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/inicio"));
    }

    @WithMockUser
    @Test
    void buscarEventos_porTitulo_debeFiltrarPorTitulo() throws Exception {
        String titulo = "Torneo";
        when(eventoRepository.findByTituloContainingIgnoreCase(titulo)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/inicio").param("titulo", titulo).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("inicio"))
                .andExpect(model().attribute("titulo", titulo))
                .andExpect(model().attributeExists("eventos"));
    }

}
