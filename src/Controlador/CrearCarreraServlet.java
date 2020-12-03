package Controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.CarreraBean;
import ModeloDAO.CarreraDAO;

/**
 * Servlet implementation class CrearCarrera
 */
@WebServlet("/CrearCarrera")
public class CrearCarreraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrearCarreraServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		PrintWriter out = response.getWriter();  
		
		HttpSession session_user=request.getSession();    
		if (session_user.getAttribute("usuario") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}else{
			int rol = (int)session_user.getAttribute("rol");
			if(rol != 1){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}else{
				String clave = request.getParameter("clave_carrera");  
	    	    String nombre = request.getParameter("nombre");
	            int id_turno = Integer.parseInt(request.getParameter("id_turno"));
	    			
	            CarreraBean c = new CarreraBean();  
	            c.setClave_carrera(clave);
	            c.setNombre(nombre);  
	            c.setIdTurno(id_turno);
	            
	            int status = CarreraDAO.save(c);  
	            if(status>0){  
	                out.print("<p style='color: green; font-weight: bold;'>¡Registro guardado con éxito!</p>");  
	                request.getRequestDispatcher("administrador/crear_carrera.jsp").include(request, response);  
	            }else{  
	                out.println("<p style='color: red; font-weight: bold;'>Sorry! Registro no guardado!</p>"); 
	                request.getRequestDispatcher("administrador/crear_carrera.jsp").include(request, response); 
	            }	
			} 
		}
	}

}
