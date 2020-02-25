package servelts;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.ManagerLista;
import objetos.Lista;

@WebServlet("/agregarlista")
public class AgregarLista extends HttpServlet {
		private static final long serialVersionUID = 1L;

	    public AgregarLista() {
	        super();
	    }


		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String id_usuario = null;
			
			String id_pelicula = null;
			
			id_usuario=request.getParameter("id_usuario");
			id_pelicula=request.getParameter("id_pelicula");
			
			Lista lista = new Lista(id_usuario, id_pelicula);
			
			ManagerLista.agregarLista(lista);
			
			request.getSession().setAttribute("id_usuario", id_usuario);
			
			RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
			rd.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}
