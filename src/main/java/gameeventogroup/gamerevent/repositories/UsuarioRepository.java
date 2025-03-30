package gameeventogroup.gamerevent.repositories;

import org.springframework.data.repository.CrudRepository;
import gameeventogroup.gamerevent.models.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByNombreUsuario(String nombreUsuario);

}

