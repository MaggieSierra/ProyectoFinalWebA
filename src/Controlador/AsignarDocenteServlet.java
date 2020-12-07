package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AsignarDocenteServlet
 */
@WebServlet("/AsignarDocenteServlet")
public class AsignarDocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AsignarDocenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int status = 0;
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
				int id_carrera = Integer.parseInt(request.getParameter("id_carrera"));
	            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
	    			
	            try {
	    			Connection con = Conexion.getConnection();
	    			PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario_carrera WHERE id_carrera = ? AND id_usuario = ? ");
	    			ps.setInt(1, id_carrera);
	    			ps.setInt(2, id_usuario);
	    			ResultSet rs = ps.executeQuery();
	    			
	    			if(!rs.next()) {
	    				ps = con.prepareStatement("INSERT INTO usuario_carrera(id_carrera, id_usuario) VALUES (?,?)");
	    				ps.setInt(1, id_carrera);
	    				ps.setInt(2, id_usuario);
		    			
	    				status = ps.executeUpdate();
	    				if(status>0){  
	    	                out.print("<p style='color: green; font-weight: bold;'>¡Docente asignado con éxito!</p>");  
	    	                request.getRequestDispatcher("asignar_carrera.jsp?id="+id_carrera).include(request, response);  
	    	            }else{  
	    	                out.println("<p style='color: red; font-weight: bold;'>Sorry! Docente no asignado!</p>"); 
	    	                request.getRequestDispatcher("asignar_carrera.jsp?id="+id_carrera).include(request, response); 
	    	            }	
	    				
	    			}else {
	    				out.println("<p style='color: orange; font-weight: bold;'>El docente ya está asignado a la carrera</p>"); 
		                request.getRequestDispatcher("asignar_carrera.jsp?id="+id_carrera).include(request, response); 
	    			}
	    		} catch (Exception ex) {
	    			ex.printStackTrace();
	    		}
	            
			} 
		}
	}

}
