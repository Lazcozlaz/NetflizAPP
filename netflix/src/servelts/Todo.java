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

import com.google.gson.Gson;

import managers.ManagerLista;
import objetos.Busqueda;
import objetos.Lista;
import objetos.Parseadores;
import objetos.Pelicula;

@WebServlet("/todo")
public class Todo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Todo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int puntuacion = 0;
		String accion = accion = request.getParameter("a");
		String busquedaJson;
		String api = request.getParameter("api");
		String id_pelicula = request.getParameter("id_pelicula");
		
		System.out.println(accion);
		System.out.println(api);
		System.out.println(id_pelicula);

		if (request.getParameter("a") != null) {
			if (accion.equals("s")) {
				puntuacion = Integer.valueOf(request.getParameter("puntos"));
				if (puntuacion != 0) {
					Lista l = new Lista(Integer.valueOf(api), id_pelicula, puntuacion);
					ManagerLista.agregarLista(l);
					String json = new Gson().toJson(l);
					response.getWriter().append(json);

					/*RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
					rd.forward(request, response);*/
				} else {
					Lista l = new Lista(Integer.valueOf(api), id_pelicula, 0);
					ManagerLista.agregarLista(l);
					String json = new Gson().toJson(l);
					response.getWriter().append(json);
/*
					RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
					rd.forward(request, response);*/
				}
			}
			if (accion.equals("g")) {
				ArrayList<Lista> lista = ManagerLista.consultarListas(Integer.valueOf(api));
				ArrayList<Pelicula> p = new ArrayList<Pelicula>();

				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).getPuntos() == 0) {

						String url = "http://www.omdbapi.com/?i=" + lista.get(i).getClave().getId_pelicula()
								+ "&apikey=40d994a0";
						try {
							busquedaJson = peticionHttpGet(url);
							p.add(Parseadores.parseadorID(busquedaJson));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				/*Busqueda b = new Busqueda(p);
				request.getSession().setAttribute("busqueda", b);
				request.getSession().setAttribute("lista", lista);
				RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
				rd.forward(request, response);*/
				
				String json = new Gson().toJson(p);
				response.getWriter().append(json);

			}

			
			if (accion.equals("l")) {

				ArrayList<Lista> lista = ManagerLista.consultarListas(Integer.valueOf(api));
				ArrayList<Pelicula> p = new ArrayList<Pelicula>();

				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).getPuntos() != 0) {

						String url = "http://www.omdbapi.com/?i=" + lista.get(i).getClave().getId_pelicula()
								+ "&apikey=40d994a0";
						try {
							busquedaJson = peticionHttpGet(url);
							p.add(Parseadores.parseadorID(busquedaJson));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}/*
				Busqueda b = new Busqueda(p);
				request.getSession().setAttribute("busqueda", b);
				request.getSession().setAttribute("lista", lista);
				RequestDispatcher rd = request.getRequestDispatcher("Puntuadas.jsp");
				rd.forward(request, response);*/

				String json = new Gson().toJson(p);
				response.getWriter().append(json);

			}
		}
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}