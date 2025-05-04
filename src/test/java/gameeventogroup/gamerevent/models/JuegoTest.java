package gameeventogroup.gamerevent.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class JuegoTest {
    @Test
    public void testGettersAndSetters() {
        Juego juego = new Juego();

        Long id = 10L;
        String nombre = "League of Legends";

        Usuario usuario = new Usuario(); 

        juego.setIdJuego(id);
        juego.setNombre(nombre);
        juego.setUsuarios(Set.of(usuario));

        assertEquals(id, juego.getIdJuego());
        assertEquals(nombre, juego.getNombre());
        assertEquals(1, juego.getUsuarios().size());
        assertTrue(juego.getUsuarios().contains(usuario));
    }

}
