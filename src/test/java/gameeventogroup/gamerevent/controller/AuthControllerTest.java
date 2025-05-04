package gameeventogroup.gamerevent.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void mostrarLogin_sinParametros() throws Exception {
        mockMvc.perform(get("/login").with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("login"))
            .andExpect(model().attribute("error", false))
            .andExpect(model().attribute("logout", false));
    }

    @Test
    void mostrarLogin_conErrorYLogout() throws Exception {
        mockMvc.perform(get("/login?error=true&logout=true").with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("login"))
            .andExpect(model().attribute("error", true))
            .andExpect(model().attribute("logout", true));
    }
}
