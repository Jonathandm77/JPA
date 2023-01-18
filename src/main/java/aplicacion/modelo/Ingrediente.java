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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
 

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name="Ingredientes")
public class Ingrediente {
	
	
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="vegano")
	private boolean vegano;
	
	@ManyToMany(cascade = { CascadeType.MERGE },  fetch =FetchType.EAGER)
    @JoinTable(
        name = "bocadillos_ingredientes", 
        joinColumns = { @JoinColumn(name = "id_ingrediente") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_bocadillo") }
    )
	@JsonIgnore
	private Set<Bocadillo> bocadillos;
	
  	public Ingrediente() {
		
	}
	
	public Ingrediente(String n, boolean v) {
		nombre = n;
		bocadillos = new HashSet<Bocadillo>();
		vegano = v;
		  
	}


	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


 
	
	public Set<Bocadillo> getBocadillos() {
		return bocadillos;
	}

	public void setBocadillos(Set<Bocadillo> bocadillos) {
		this.bocadillos = bocadillos;
	}

	@Override
	public String toString() {
		if(vegano) {
			return nombre+"---------- Vegano: SI\n";	
		}else {
			return nombre+"---------- Vegano: NO\n";	
		}
					 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	 
	
}
