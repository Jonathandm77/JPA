package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "precio")
	private double precio;

	@ManyToOne // (cascade = { CascadeType.ALL },optional = true)
	@JoinColumn(name = "id_alumno", nullable = true)
	@JsonIgnore
	private Alumno alumno;

	@ManyToMany(mappedBy = "pedidos", fetch = FetchType.EAGER)
	private Set<Bocadillo> bocadillos;

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Pedido() {
		bocadillos = new HashSet<Bocadillo>();
	}

	public Pedido(Alumno a) {
		alumno = a;
		bocadillos = new HashSet<Bocadillo>();

	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Set<Bocadillo> getBocadillos() {
		return bocadillos;
	}

	public void setBocadillos(Set<Bocadillo> bocadillos) {
		this.bocadillos = bocadillos;
	}

	@Override
	public String toString() {
		return imprimir();
	}

	public String imprimir() {

		String resultado = "Boccadillos del pedido " + id + "\n";
		if (bocadillos.size() == 0) {

		} else {
			for (Bocadillo b : bocadillos) {
				resultado = resultado + b.toString();
			}
		}
		return resultado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void calcularPrecio() {
		double resultado = 0.0;

		for (Bocadillo b : bocadillos) {
			resultado += b.getPrecio();
		}
		precio = resultado;
	}


}
