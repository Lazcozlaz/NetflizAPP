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

@WebServlet("/procesartitulo")
public class ProcesarTitulo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProcesarTitulo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String titulo=null;
		titulo= request.getParameter("titulo");
		String url = "http://www.omdbapi.com/?s=" + titulo + "&apikey=40d994a0";
			
						String busquedaJson;
						try {
							busquedaJson = peticionHttpGet(url);
							Busqueda busqueda = Parseadores.parseadorBusqueda(busquedaJson);

							String json = new Gson().toJson(busqueda.getPeliculas());
							response.getWriter().append(json);
							
							request.getSession().setAttribute("busqueda", busqueda);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						

						/*
						RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
						rd.forward(request, response);
						*/
					
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



