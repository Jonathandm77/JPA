package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
 
import aplicacion.modelo.Bocadillo;

public interface BocadilloRepo extends JpaRepository<Bocadillo, Integer> {

	public Optional<Bocadillo> findByNombre(String nombre);
	
}
