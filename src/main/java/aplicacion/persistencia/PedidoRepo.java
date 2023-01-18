package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
 
import aplicacion.modelo.Bocadillo;
import aplicacion.modelo.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {

	// public Optional<Bocadillo> findByNombre(String nombre);
	
}
