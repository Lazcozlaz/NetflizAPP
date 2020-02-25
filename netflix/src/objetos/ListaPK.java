package objetos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ListaPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column(name="id_usuario")
	int id_usuario;
	@Column(name="id_pelicula")
	String id_pelicula;
	
	public ListaPK() {
	}
	
	public ListaPK(int id_usuario, String id_pelicula) {
		super();
		this.id_usuario = id_usuario;
		this.id_pelicula = id_pelicula;
	}
	
	@Override
	  public int hashCode() {
		  int codigo=this.id_usuario + this.id_pelicula.hashCode();
		  return codigo;
	  }
	
	 @Override
	  public boolean equals(Object obj) {
		  boolean iguales=false;
		  ListaPK lista2=(ListaPK)obj;
		  if(lista2.id_usuario==this.id_usuario) {
			  if(lista2.id_pelicula.compareTo(this.id_pelicula)==0) {
				  iguales=true;
			  }
		  } 
		  return iguales;
	  }

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(String id_pelicula) {
		this.id_pelicula = id_pelicula;
	}
	 
	 
}
