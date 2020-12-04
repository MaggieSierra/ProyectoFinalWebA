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

import Modelo.MateriaBean;
import ModeloDAO.MateriaDAO;

/**
 * Servlet implementation class CrearMateriaServlet
 */
@WebServlet("/CrearMateriaServlet")
public class CrearMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearMateriaServlet() {
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
		
			String user = (String)session_user.getAttribute("usuario");
			int rol = (int)session_user.getAttribute("rol");
			if(rol != 2){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}else{
				int id_carrera = Integer.parseInt(request.getParameter("id_carrera"));
				String clave = request.getParameter("clave_materia");  
	    	    String nombre = request.getParameter("nombre");
	            int semestre = Integer.parseInt(request.getParameter("semestre"));
	            int hrs_t = Integer.parseInt(request.getParameter("hrs_t"));
	            int hrs_p = Integer.parseInt(request.getParameter("hrs_p"));
	            int creditos = Integer.parseInt(request.getParameter("creditos"));
	    			
	            MateriaBean m = new MateriaBean(); 
	            m.setId_carrera(id_carrera);
	            m.setClave_materia(clave);
	            m.setNombre(nombre);  
	            m.setSemestre(semestre);
	            m.setHrs_teoria(hrs_t);
	            m.setHrs_practica(hrs_p);
	            m.setCreditos(creditos);
	            
	            int status = MateriaDAO.save(m);  
	            if(status>0){  
	                out.print("<p style='color: green; font-weight: bold;'>�Registro guardado con �xito!</p>");  
	                request.getRequestDispatcher("crear_materia.jsp").include(request, response);  
	            }else{  
	                out.println("<p style='color: red; font-weight: bold;'>Sorry! Registro no guardado!</p>"); 
	                request.getRequestDispatcher("crear_materia.jsp").include(request, response); 
	            }	
			} 
		}
	}

}
