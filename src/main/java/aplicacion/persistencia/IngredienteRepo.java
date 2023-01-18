package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
 
import aplicacion.modelo.Bocadillo;
import aplicacion.modelo.Ingrediente;

public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {

	public Optional<Bocadillo> findByNombre(String nombre);
	
}
