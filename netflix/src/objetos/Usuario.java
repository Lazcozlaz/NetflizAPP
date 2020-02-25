package objetos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table ( name = "usuario" , schema = "netflix" )
public class Usuario {
	
	@Id
	@Column(name="id_usuario")
	String id;
	@Column(name="contrasena")
	String contrasena;
	@Column(name="api")
	int api;
	
	public Usuario(String id, String contrasena, int api) {
		super();
		this.id = id;
		this.contrasena = contrasena;
		this.api = api;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public Usuario(String id, String contrasena) {
		super();
		this.id = id;
		this.contrasena = contrasena;
	}
	
	public int getApi() {
		return api;
	}

	public void setApi(int api) {
		this.api = api;
	}

	public Usuario() {
		
	}

	

}
