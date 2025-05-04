package gameeventogroup.gamerevent.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ServicioEventoTest {
    @Test
    public void testGettersAndSetters() {
        ServicioEvento servicio = new ServicioEvento();

        Long id = 100L;
        Boolean estacionamiento = true;
        Boolean locomocion = false;
        Boolean comidas = true;
        Boolean alojamiento = false;

        Evento evento = new Evento(); 

        servicio.setIdEvento(id);
        servicio.setEstacionamiento(estacionamiento);
        servicio.setLocomocion(locomocion);
        servicio.setComidas(comidas);
        servicio.setAlojamiento(alojamiento);
        servicio.setEvento(evento);

        assertEquals(id, servicio.getIdEvento());
        assertEquals(estacionamiento, servicio.getEstacionamiento());
        assertEquals(locomocion, servicio.getLocomocion());
        assertEquals(comidas, servicio.getComidas());
        assertEquals(alojamiento, servicio.getAlojamiento());
        assertEquals(evento, servicio.getEvento());
    }
}
