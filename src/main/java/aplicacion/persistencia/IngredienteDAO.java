package aplicacion.persistencia;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
 
import aplicacion.modelo.Ingrediente;
import aplicacion.utils.JPAUtil;
 

public class IngredienteDAO {

	
	 
	

		
	public void insertaringredienteJPA(Ingrediente ingrediente) {
				
		//JPA
	    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(ingrediente);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		 
		
		
	}
	
	 
		
	
	public void modificaringredienteJPA(Ingrediente ingrediente) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(ingrediente);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		
	}
	
	 
		
	public void eliminaringredienteJPA(Ingrediente ingrediente) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		
		em.remove(em.contains(ingrediente)? ingrediente:em.merge(ingrediente) );
		
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
				
	}
 
		
	public ArrayList<Ingrediente> listaringredienteJPA() {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			
			ArrayList<Ingrediente> misAlumnos = (ArrayList<Ingrediente>) em.createQuery("from ingrediente").getResultList();
			em.getTransaction().commit();
			return misAlumnos;
			
			}
			catch(PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
		
		return null;
		
	}
	
	
	
	
	 
	
	public Ingrediente buscaringredientePorIdJPA(int id) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Ingrediente ingrediente = em.find(Ingrediente.class, id);
			System.out.println("El alumno buscado se llama :"+ingrediente.getNombre());
			return ingrediente;
			}
			catch(PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
			return null;		
	}
	
	
	
	public void imprimiringrediente(ArrayList<Ingrediente> misingredientes) {
		System.out.println("Listado de Ingredientes");
		for(Ingrediente a:misingredientes) {
			a.toString();
		}
	}
	
}
