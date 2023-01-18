package aplicacion.persistencia;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
 

import aplicacion.modelo.Bocadillo;
import aplicacion.utils.JPAUtil;
 
 

public class BocadilloDAO {

	 
		
	public void insertarBocadilloJPA(Bocadillo bocadillo) {
				
		//JPA
	    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(bocadillo);
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
	
	 
		
	
	public void modificarBocadilloJPA(Bocadillo bocadillo) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(bocadillo);
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
	
	 
		
	public void eliminarBocadilloJPA(Bocadillo bocadillo) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		
		em.remove(em.contains(bocadillo)? bocadillo:em.merge(bocadillo) );
		
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
	
	 
		
	public ArrayList<Bocadillo> listarBocadilloJPA() {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			
			ArrayList<Bocadillo> misAlumnos = (ArrayList<Bocadillo>) em.createQuery("from Bocadillo").getResultList();
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
	
	
	
	
	
	 
	
	public Bocadillo buscarBocadilloPorIdJPA(int id) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Bocadillo pedido = em.find(Bocadillo.class, id);
			System.out.println("El alumno buscado se llama :"+pedido.getNombre());
			return pedido;
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
	
	
	
	public void imprimirBocadillo(ArrayList<Bocadillo> misBocadillos) {
		System.out.println("Listado de Alumnos");
		for(Bocadillo a:misBocadillos) {
			a.toString();
		}
	}
	
}
