package gameeventogroup.gamerevent.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExpositorTest {

     @Test
    public void testGettersAndSetters() {
        Expositor expositor = new Expositor();

        Long id = 7L;
        String nombre = "María López";
        String descripcion = "Experta en desarrollo de videojuegos";

        Evento evento = new Evento(); 

        expositor.setIdExpositor(id);
        expositor.setNombre(nombre);
        expositor.setDescripcion(descripcion);
        expositor.setEvento(evento);

        assertEquals(id, expositor.getIdExpositor());
        assertEquals(nombre, expositor.getNombre());
        assertEquals(descripcion, expositor.getDescripcion());
        assertEquals(evento, expositor.getEvento());
    }

}
