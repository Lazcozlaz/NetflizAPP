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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import objetos.PeticionWeb;
import objetos.Busqueda;
import objetos.Parseadores;
import objetos.Pelicula;

@WebServlet("/procesarposter")
public class ProcesarPoster extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProcesarPoster() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String id= request.getParameter("id_pelicula");
		//int api= Integer.valueOf(request.getParameter("api"));
		String id_usuario= (String)request.getParameter("id_usuario");
		String url = "http://www.omdbapi.com/?i=" + id + "&apikey=40d994a0";
		System.out.println(id);
		//System.out.println(api);
		System.out.println(id_usuario);
			
						String busquedaJson;
						try {
							busquedaJson = peticionHttpGet(url);
							System.out.println(busquedaJson);
							Pelicula pelicula = Parseadores.parseadorID(busquedaJson);
							
							String json = new Gson().toJson(pelicula);
							response.getWriter().append(json);
							request.getSession().setAttribute("pelicula", pelicula);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						/*request.getSession().setAttribute("id_usuario", id_usuario);
						request.getSession().setAttribute("api", api);
						RequestDispatcher rd = request.getRequestDispatcher("Pelicula.jsp");
						rd.forward(request, response);*/
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


