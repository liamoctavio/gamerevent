package gameeventogroup.gamerevent.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import java.util.Set;


public class EventoTest {

    @Test
    public void testGettersAndSetters() {
        Evento evento = new Evento();
        evento.setIdEvento(1L);
        evento.setTitulo("Conferencia");
        evento.setDescripcion("Evento sobre tecnología");
        evento.setDireccion("Av. Siempre Viva 123");
        evento.setFecha(LocalDate.of(2025, 5, 10));
        evento.setHora(LocalTime.of(18, 30));
        evento.setCategoria("Tech");
        evento.setCiudad("Santiago");
        evento.setPais("Chile");

        Organizador org = new Organizador(); 
        evento.setOrganizadores(Set.of(org));

        ServicioEvento servicio = new ServicioEvento();
        evento.setServicios(servicio);

        Expositor expositor = new Expositor();
        evento.setExpositores(Set.of(expositor));

        assertEquals(1L, evento.getIdEvento());
        assertEquals("Conferencia", evento.getTitulo());
        assertEquals("Evento sobre tecnología", evento.getDescripcion());
        assertEquals("Av. Siempre Viva 123", evento.getDireccion());
        assertEquals(LocalDate.of(2025, 5, 10), evento.getFecha());
        assertEquals(LocalTime.of(18, 30), evento.getHora());
        assertEquals("Tech", evento.getCategoria());
        assertEquals("Santiago", evento.getCiudad());
        assertEquals("Chile", evento.getPais());
        assertEquals(1, evento.getOrganizadores().size());
        assertEquals(servicio, evento.getServicios());
        assertEquals(1, evento.getExpositores().size());
    }

}
