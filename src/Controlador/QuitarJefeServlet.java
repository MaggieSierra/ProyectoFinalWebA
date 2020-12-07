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
 * Servlet implementation class QuitarJefeServlet
 */
@WebServlet("/QuitarJefeServlet")
public class QuitarJefeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuitarJefeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session_user=request.getSession();   
		PrintWriter out = response.getWriter();
		if (session_user.getAttribute("usuario") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}else{
		
			int rol = (int)session_user.getAttribute("rol");
			if(rol != 1){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}else{
	            int id = Integer.parseInt(request.getParameter("id"));  
	    			
	            try {
	    			Connection con = Conexion.getConnection();
	    			PreparedStatement ps = con.prepareStatement("SELECT * FROM jefe_carrera WHERE id_jefe_carrera = ?");
	    			ps.setInt(1, id );
	    			ResultSet rs = ps.executeQuery();
	    			if (rs.next()) {
	    				ps = con.prepareStatement("DELETE FROM jefe_carrera WHERE id_jefe_carrera = ?");
		    			ps.setInt(1, id );
		    			ps.executeUpdate();
		    			
	    				int id_usuario = rs.getInt(2);
	    				
	    				ps = con.prepareStatement("SELECT * FROM jefe_carrera WHERE id_usuario = ?");
		    			ps.setInt(1, id_usuario );
		    			rs = ps.executeQuery();
		    			if (!rs.next()) {
		    				ps = con.prepareStatement("UPDATE usuario SET id_rol = (SELECT id_rol FROM rol WHERE nombre_rol='Docente')"
			    					+ " WHERE id_usuario = ?");
			    			ps.setInt(1, id_usuario);
		    				ps.executeUpdate();
		    			}
		    			
		    			out.print("<p style='color: red; font-weight: bold;'>¡Registro eliminado correctamente!</p>");  
		                request.getRequestDispatcher("jefes.jsp").include(request, response);
	    			}
	    			
	    		} catch (Exception ex) {
	    			ex.printStackTrace();
	    		}
	            
			} 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
