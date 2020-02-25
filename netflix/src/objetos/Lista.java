package objetos;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table ( name = "lista" , schema = "netflix" )
public class Lista {
		
		@EmbeddedId
		ListaPK clave;
		@Column(name="puntuacion")
		int puntos;
		
		public Lista() {
		}
		
		public Lista(int id_usuario, String id_pelicula) {
			super();
			ListaPK lpk= new ListaPK(id_usuario, id_pelicula);
			this.clave=lpk;
		}

		public Lista(int id_usuario, String id_pelicula, int puntos) {
			super();
			ListaPK lpk= new ListaPK(id_usuario, id_pelicula);
			this.clave=lpk;
			this.puntos=puntos;
		}

		public ListaPK getClave() {
			return clave;
		}

		public void setClave(ListaPK clave) {
			this.clave = clave;
		}

		public int getPuntos() {
			return puntos;
		}

		public void setPuntos(int puntos) {
			this.puntos = puntos;
		}
		
		
		
		
	}
