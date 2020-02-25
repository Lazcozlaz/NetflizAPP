package managers;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import objetos.Lista;
import objetos.Usuario;

public class ManagerLista {
	
	private static EntityManager entityManager;

	public static void agregarLista(Lista lista) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(lista);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static ArrayList<String> consultarLista(int usuario) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ArrayList<String> listaArray = null;
		return listaArray = (ArrayList<String>) entityManager.createQuery("SELECT l.clave.id_pelicula FROM objetos.Lista l WHERE l.clave.id_usuario='" + usuario + "'").getResultList();
		
	}
	
	public static ArrayList<Lista> consultarlista(int usuario) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ArrayList<Lista> listaArray = null;
		return listaArray = (ArrayList<Lista>) entityManager.createQuery("SELECT l FROM objetos.Lista l WHERE l.clave.id_usuario='" + usuario + "'").getResultList();
		
	}
	
	public static ArrayList<Lista> consultarListas(int usuario) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ArrayList<Lista> listaArray = null;
		return listaArray = (ArrayList<Lista>) entityManager.createQuery("SELECT l FROM objetos.Lista l WHERE l.clave.id_usuario='" + usuario + "'").getResultList();
		
	}
}