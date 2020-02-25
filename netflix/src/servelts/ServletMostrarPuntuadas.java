package servelts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.ManagerLista;
import objetos.Busqueda;
import objetos.Lista;
import objetos.Parseadores;
import objetos.Pelicula;

	@WebServlet("/mostrarpuntuadas")
	public class ServletMostrarPuntuadas extends HttpServlet {
		private static final long serialVersionUID = 1L;

	    public ServletMostrarPuntuadas() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				

			String id_usuario =request.getParameter("id");
			
			ArrayList<Lista> lista= ManagerLista.consultarListas(id_usuario);
			
			ArrayList<String> id_pelis =ManagerLista.consultarLista(id_usuario);
			
			
			ArrayList <Pelicula> p = new ArrayList<Pelicula>();
			
			for (int i = 0; i < lista.size(); i++) {
				String url = "http://www.omdbapi.com/?i=" + lista.get(i).getClave().getId_pelicula() + "&apikey=40d994a0";
				
				String busquedaJson;
				try {
					busquedaJson = peticionHttpGet(url);
					p.add(Parseadores.parseadorID(busquedaJson));
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			Busqueda b = new Busqueda(p);

			request.getSession().setAttribute("busqueda", b);

			request.getSession().setAttribute("lista", lista);
							RequestDispatcher rd = request.getRequestDispatcher("Puntuadas.jsp");
							rd.forward(request, response);
							
						
		}
		public static String peticionHttpGet(String urlParaVisitar) throws Exception {
			  // Esto es lo que vamos a devolver
			  StringBuilder resultado = new StringBuilder();
			  // Crear un objeto de tipo URL
			  URL url = new URL(urlParaVisitar);

			  // Abrir la conexión e indicar que será de tipo GET
			  HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			  conexion.setRequestMethod("GET");
			  // Búferes para leer
			  BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			  String linea;
			  // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
			  while ((linea = rd.readLine()) != null) {
			    resultado.append(linea);
			  }
			  // Cerrar el BufferedReader
			  rd.close();
			  // Regresar resultado, pero como cadena, no como StringBuilder
			  return resultado.toString();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}



