package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.HorarioBean;
import ModeloDAO.HorarioDAO;

/**
 * Servlet implementation class CrearHorarioServlet
 */
@WebServlet("/CrearHorarioServlet")
public class CrearHorarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrearHorarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int id_materia = Integer.parseInt(request.getParameter("id_materia")); 
	    String periodo = request.getParameter("periodo");
	    String grupo = request.getParameter("grupo");
	    int num_alumnos = Integer.parseInt(request.getParameter("alumnos"));
	    String aula = request.getParameter("aula");
	    String lunes = request.getParameter("lunes");
	    String martes = request.getParameter("martes");
	    String miercoles = request.getParameter("miercoles");
	    String jueves = request.getParameter("jueves");
	    String viernes = request.getParameter("viernes");
        
		try {
			Connection con = Conexion.getConnection();
			String sql = "SELECT horario.* FROM horario WHERE id_usuario = ? AND lunes = ? OR martes = ?"
					+ " miercoles = ? OR jueves = ? OR viernes = ? ORDER BY id_horario;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id_usuario);
			ps.setString(2, lunes);
			ps.setString(3, martes);
			ps.setString(4, miercoles);
			ps.setString(5, jueves);
			ps.setString(6, viernes);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				HorarioBean h = new HorarioBean();
	            h.setId_usuario(id_usuario);
	            h.setId_materia(id_materia);
	            h.setPeriodo(periodo);
	            h.setGrupo(grupo);
	            h.setNum_alumnos(num_alumnos);
	            h.setAula(aula);
	            h.setLunes(lunes);
	            h.setMartes(martes);
	            h.setMiercoles(miercoles);
	            h.setJueves(jueves);
	            h.setViernes(viernes);
	            
	            int status = HorarioDAO.save(h);  
	            if(status>0){  
	                out.print("<p style='color: green; font-weight: bold;'>¡Registro guardado con éxito!</p>");  
	                request.getRequestDispatcher("ingresar_horario.jsp").include(request, response);  
	            }else{  
	                out.println("<p style='color: red; font-weight: bold;'>Sorry! Registro no guardado!</p>"); 
	                request.getRequestDispatcher("ingresar_horario.jsp").include(request, response); 
	            }	
				
			}else {
				out.println("<p style='color: red; font-weight: bold;'>El horario del docente se empalma</p>"); 
                request.getRequestDispatcher("ingresar_horario.jsp").include(request, response); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
