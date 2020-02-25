package servelts;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.UsuarioManager;
import objetos.Usuario;
import objetos.Utilidades;


@WebServlet("/procesarlogin")

public class ProcesarMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ProcesarMain() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username=request.getParameter("id");
		String contrasena=Utilidades.MD5(request.getParameter("contra"));
		int api = username.hashCode();
		
		Usuario usuario=new Usuario(username,contrasena, api);
		
		boolean existe = UsuarioManager.consultarUsuario(usuario);
		
		if(existe) {
			request.getSession().setAttribute("usuario", usuario);
			request.getSession().setAttribute("api", api);
			System.out.println(api);
			RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
