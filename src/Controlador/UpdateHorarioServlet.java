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
 * Servlet implementation class UpdateHorarioServlet
 */
@WebServlet("/UpdateHorarioServlet")
public class UpdateHorarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateHorarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id_horario = Integer.parseInt(request.getParameter("id"));
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
			String sql = "SELECT horario.* FROM horario WHERE lunes = ? OR martes = ?"
					+ " miercoles = ? OR jueves = ? OR viernes = ? AND id_usuario = ? AND id_horario != ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, lunes);
			ps.setString(2, martes);
			ps.setString(3, miercoles);
			ps.setString(4, jueves);
			ps.setString(5, viernes);
			ps.setInt(6, id_usuario);
			ps.setInt(7, id_horario);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				HorarioBean h = new HorarioBean();
				h.setId_horario(id_horario);
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
	            
	            int status = HorarioDAO.update(h); 
	            if(status>0){  
	                out.print("<p style='color: green; font-weight: bold;'>¡Horario actualizado con éxito!</p>");  
	                request.getRequestDispatcher("crear_horario.jsp").include(request, response);  
	            }else{  
	                out.println("<p style='color: red; font-weight: bold;'>Sorry! Horario no actualizado!</p>"); 
	                request.getRequestDispatcher("crear_horario.jsp").include(request, response); 
	            }	
				
			}else {
				out.println("<p style='color: red; font-weight: bold;'>El horario del docente se empalma</p>"); 
                request.getRequestDispatcher("crear_horario.jsp").include(request, response); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
