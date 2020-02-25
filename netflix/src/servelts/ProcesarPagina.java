package servelts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objetos.Busqueda;
import objetos.Parseadores;

@WebServlet("/procesarpagina")
public class ProcesarPagina extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProcesarPagina() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String titulo=null;
		String page = null;
		titulo = (String)request.getParameter("title");
		System.out.println(titulo);
		page = request.getParameter("page");
		System.out.println(page);
		String url = "http://www.omdbapi.com/?s="+titulo+"&page="+page+"&apikey=40d994a0";
			
						String busquedaJson;
						try {
							busquedaJson = peticionHttpGet(url);
							Busqueda busqueda = Parseadores.parseadorBusqueda(busquedaJson);
							
							request.getSession().setAttribute("busqueda", busqueda);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						RequestDispatcher rd = request.getRequestDispatcher("Principal.jsp");
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



