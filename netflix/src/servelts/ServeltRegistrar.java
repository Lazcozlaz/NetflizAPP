package servelts;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import objetos.Usuario;
import objetos.Utilidades;
import managers.UsuarioManager;


@WebServlet("/Registrar")
public class ServeltRegistrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServeltRegistrar() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = null;
		
		String contrasena = null;
		
		usuario=request.getParameter("user");
		contrasena=Utilidades.MD5(request.getParameter("pass"));
		int api = usuario.hashCode();
		
		Usuario usu = new Usuario(usuario, contrasena, api);
		boolean existe = UsuarioManager.consultarUsuario(usu);
		
		if(!existe) {
			UsuarioManager.crearUsuario(usu);
		}
		
		String json = new Gson().toJson(usu);
		response.getWriter().append(json);
		/*
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
