package gameeventogroup.gamerevent.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class OrganizadorTest {
    @Test
    public void testGettersAndSetters() {
        Organizador organizador = new Organizador();

        Long id = 42L;
        String nombre = "Juan Pérez";
        String contacto = "juan@example.com";

        Evento evento = new Evento(); 

        organizador.setIdOrganizador(id);
        organizador.setNombre(nombre);
        organizador.setContacto(contacto);
        organizador.setEventos(Set.of(evento));

        assertEquals(id, organizador.getIdOrganizador());
        assertEquals(nombre, organizador.getNombre());
        assertEquals(contacto, organizador.getContacto());
        assertNotNull(organizador.getEventos());
        assertEquals(1, organizador.getEventos().size());
        assertTrue(organizador.getEventos().contains(evento));
    }
}
