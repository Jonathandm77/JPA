package aplicacion.servicio.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aplicacion.modelo.Usuario;

@Repository
public interface RegistroService extends JpaRepository<Usuario, Integer> {
    
}

