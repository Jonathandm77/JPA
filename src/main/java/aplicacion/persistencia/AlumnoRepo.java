package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Alumno;
import aplicacion.modelo.Bocadillo;

public interface AlumnoRepo extends JpaRepository<Alumno, Integer> {

	public Optional<Bocadillo> findByNombre(String nombre);
	
}
