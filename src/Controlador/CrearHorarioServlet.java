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
import java.util.List;

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
	    
	    List<HorarioBean> horario = HorarioDAO.getHorarioByIdUser(id_usuario);
		boolean empalman = false;
		for (HorarioBean tempHorario: horario) {
			if (tempHorario.getLunes() != null && lunes != null && !tempHorario.getLunes().equals("") && !lunes.equals("")) {
				if (tempHorario.getLunes().equals(lunes)) {
					empalman = true;
				}
			}
			
			if (tempHorario.getMartes() != null && martes != null && !tempHorario.getMartes().equals("") && !martes.equals("")) {
				if (tempHorario.getMartes().equals(martes)) {
					empalman = true;
				}
			}
			
			if (tempHorario.getMiercoles() != null && miercoles != null && !tempHorario.getMiercoles().equals("") && !miercoles.equals("")) {
				if (tempHorario.getMiercoles().equals(miercoles)) {
					empalman = true;
				}
			}
			
			if ((tempHorario.getJueves() != null && jueves != null) && !tempHorario.getJueves().equals("") && !jueves.equals("")) {
				if (tempHorario.getJueves().equals(jueves)) {
					empalman = true;
				}
			}
			
			if (tempHorario.getViernes() != null && viernes != null && !tempHorario.getViernes().equals("") && !viernes.equals("")) {
				if (tempHorario.getViernes().equals(viernes)) {
					empalman = true;
				}
			}
		}
        
		try {
			if(!empalman) {
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
		            request.getRequestDispatcher("crear_horario.jsp").include(request, response);  
		        }else{  
		            out.println("<p style='color: red; font-weight: bold;'>Sorry! Registro no guardado!</p>"); 
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
