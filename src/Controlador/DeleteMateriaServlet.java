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

import ModeloDAO.MateriaDAO;

/**
 * Servlet implementation class DeleteMateriaServlet
 */
@WebServlet("/DeleteMateriaServlet")
public class DeleteMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMateriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				int id = Integer.parseInt(request.getParameter("id"));  
	            MateriaDAO.delete(id);  
	            out.print("<p style='color: red; font-weight: bold;'>¡Registro eliminado correctamente!</p>");  
                request.getRequestDispatcher("materias.jsp").include(request, response);
			} 
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
