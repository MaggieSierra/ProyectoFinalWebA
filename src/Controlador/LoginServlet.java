package Controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.UsuarioBean;
import ModeloDAO.UsuarioDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		UsuarioBean user = new UsuarioBean();
		user.setUsuario(request.getParameter("usuario"));
		user.setContraseña(request.getParameter("password"));
		
		String pagina = "login.jsp";
		if(UsuarioDAO.validate(user)){
			UsuarioBean us = UsuarioDAO.getUsuario(user);
			HttpSession session_user = request.getSession();
			session_user.setAttribute("id_usuario", us.getId_usuario());
			session_user.setAttribute("prefijo", us.getPrefijo());
			session_user.setAttribute("usuario", us.getUsuario());
			session_user.setAttribute("nombre", us.getNombre());
			session_user.setAttribute("apellido", us.getPrimer_apellido());
			if(us.getSegundo_apellido() == null){
				session_user.setAttribute("segundo_apellido","");
			}else{
				session_user.setAttribute("segundo_apellido",us.getSegundo_apellido());
			}
			session_user.setAttribute("rol", us.getId_rol());
			pagina = "/index.jsp";
		} else {
			pagina = "/login.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}
