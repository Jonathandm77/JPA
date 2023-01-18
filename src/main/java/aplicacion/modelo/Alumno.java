package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="alumnos")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(cascade= {CascadeType.ALL}, mappedBy="alumno", fetch = FetchType.EAGER)
	private Set<Pedido> pedidos; 
	
	public Alumno(String nombre) {
		this.nombre = nombre;
		pedidos = new HashSet<Pedido>();
	}
	
	public Alumno() {
		pedidos = new HashSet<Pedido>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void imprimir() {
		
		System.out.println("Alumno id=" + id + ", nombre=" + getNombre());
		for(Pedido p: pedidos) {
			p.imprimir();
			System.out.println("");
		}
		
		
	}
	
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + "]";
	}
}
