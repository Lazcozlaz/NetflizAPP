package managers;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import objetos.Usuario;

public class UsuarioManager {

	private static EntityManager entityManager;

	public static void crearUsuario(Usuario usuario) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static boolean consultarUsuario(Usuario usuario) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ArrayList<Usuario> usuarios = null;
		usuarios = (ArrayList<Usuario>) entityManager.createQuery("SELECT u FROM objetos.Usuario u WHERE u.contrasena='" + usuario.getContrasena() + "'").getResultList();
		boolean correcto = false;
		if (!usuarios.isEmpty()) {
			correcto = true;
		}
		return correcto;
	}
	
	public static Usuario getUsuario(Usuario usuario) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ArrayList<Usuario> usuarios = null;
		usuario = (Usuario) entityManager.createQuery("SELECT u FROM objetos.Usuario u WHERE u.contrasena='" + usuario.getContrasena() + "'").getResultList();
		return usuario;
	}
	
	public static ArrayList<String> consultarApi(Usuario usuario) {
		entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		ArrayList<String> apis = null;
		apis = (ArrayList<String>) entityManager.createQuery("SELECT FROM api from usuario").getResultList();
		return apis;
	}
}