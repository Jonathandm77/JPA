package aplicacion.persistencia;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import aplicacion.modelo.Pedido;
import aplicacion.utils.JPAUtil;
 
 

public class PedidoDAO {
 
	@Autowired
	PedidoRepo pedidoRepo;
	
	
	public void insertarPedidoJPA(Pedido pedido) {
			
		//pedidoRepo.save(pedido);
		
		
		
		//JPA
	  /*  EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(pedido);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}*/
		 
		
		
	}
	
	 
	
	public void modificarPedidoJPA(Pedido pedido) {
		
		//pedidoRepo.save(pedido);
		
		//JPA
		/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(pedido);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}*/
		
	}
	
	 
		
	public void eliminarPedidoJPA(Pedido pedido) {
		
		//pedidoRepo.delete(pedido);
		
		//JPA
		/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		
		em.remove(em.contains(pedido)? pedido:em.merge(pedido) );
		
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}*/
				
	}
	
	 
		
	public ArrayList<Pedido> listarPedidoJPA() {
		
		return (ArrayList<Pedido>) pedidoRepo.findAll();
		
		/*EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			
			ArrayList<Pedido> misAlumnos = (ArrayList<Pedido>) em.createQuery("from Pedido").getResultList();
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
		
		return null;*/
		
	}
	
	
	 
		
	
	public Pedido buscarPedidoPorIdJPA(int id) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Pedido pedido = em.find(Pedido.class, id);
			System.out.println("El alumno buscado se llama :"+pedido.getId());
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
	
	
	
	public void imprimir(ArrayList<Pedido> misPedidos) {
		System.out.println("Listado de Alumnos");
		for(Pedido a:misPedidos) {
			a.toString();
		}
	}
	
}
