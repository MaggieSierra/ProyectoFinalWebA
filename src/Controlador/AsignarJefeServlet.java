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
 * Servlet implementation class AsignarJefeServlet
 */
@WebServlet("/AsignarJefeServlet")
public class AsignarJefeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AsignarJefeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	    			PreparedStatement ps = con.prepareStatement("SELECT * FROM jefe_carrera WHERE id_carrera = ?");
	    			ps.setInt(1, id_carrera);
	    			ResultSet rs = ps.executeQuery();
	    			if(!rs.next()) {
	    				ps = con.prepareStatement("SELECT * FROM usuario_carrera WHERE id_carrera = ? AND id_usuario = ? ");
		    			ps.setInt(1, id_carrera);
		    			ps.setInt(2, id_usuario);
		    			rs = ps.executeQuery();
		    			
		    			if(!rs.next()) {
		    				ps = con.prepareStatement("INSERT INTO usuario_carrera(id_carrera, id_usuario) VALUES (?,?)");
		    				ps.setInt(1, id_carrera);
		    				ps.setInt(2, id_usuario);
		    				ps.executeUpdate();
		    			}
		    			
		    			ps = con.prepareStatement("UPDATE usuario SET id_rol = (SELECT id_rol FROM rol WHERE nombre_rol='Jefe Carrera')"
		    					+ " WHERE id_usuario = ?");
		    			ps.setInt(1, id_usuario);
	    				ps.executeUpdate();
	    				
	    				ps = con.prepareStatement("INSERT INTO jefe_carrera(id_carrera, id_usuario) VALUES (?,?)");
	    				ps.setInt(1, id_carrera);
	    				ps.setInt(2, id_usuario);
	    				status = ps.executeUpdate();
	    				if(status>0){  
	    	                out.print("<p style='color: green; font-weight: bold;'>¡Registro guardado con éxito!</p>");  
	    	                request.getRequestDispatcher("asignar_jefe.jsp").include(request, response);  
	    	            }else{  
	    	                out.println("<p style='color: red; font-weight: bold;'>Sorry! Registro no guardado!</p>"); 
	    	                request.getRequestDispatcher("asignar_jefe.jsp").include(request, response); 
	    	            }	
	    				
	    			}else {
	    				out.println("<p style='color: red; font-weight: bold;'>Ya hay un jefe asignado para está carrera</p>"); 
		                request.getRequestDispatcher("asignar_jefe.jsp").include(request, response); 
	    			}
	    		} catch (Exception ex) {
	    			ex.printStackTrace();
	    		}
	            
			} 
		}
	}

}
