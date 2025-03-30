package gameeventogroup.gamerevent.repositories;

import gameeventogroup.gamerevent.models.Evento;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EventoRepository extends CrudRepository<Evento, Long> {
    List<Evento> findByTituloContainingIgnoreCase(String titulo);
    List<Evento> findByCategoriaContainingIgnoreCase(String categoria);
    List<Evento> findByCiudadContainingIgnoreCase(String ciudad);
    List<Evento> findByFecha(Date fecha);

    List<Evento> findAll();


}
