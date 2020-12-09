package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import Modelo.MateriaBean;
import ModeloDAO.MateriaDAO;

/**
 * Servlet implementation class UploadServlet2
 */
@WebServlet("/UploadServlet2")
@MultipartConfig
public class UploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	      try { 
	    	  
	    	  HttpSession session_user=request.getSession();    
				if (session_user.getAttribute("usuario") == null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
				}else{
					int rol = (int)session_user.getAttribute("rol");
					if(rol != 2){
						RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
						dispatcher.forward(request, response);
					}else{
						
						 // Parse the request to get file items.
				    	 Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
				    	 String filename = getFilename(filePart);
				    	 InputStream filecontent = filePart.getInputStream();
				    	 
				    	// read your StringBody type
				    	 BufferedReader reader = new BufferedReader( new InputStreamReader(filePart.getInputStream()));
				    	 String line ="";
				    	 String carrera =(String) session_user.getAttribute("carrera_trabajar");
				    	 while((line=reader.readLine())!=null){
				    	    // do Something with a line
				    	    System.out.println(line);
				    	    String[] datos = line.split(",");
				    	    
				    	    if(datos.length == 1) {//en excel se guarda con ; el cvs
				    	    	datos = line.split(";");
				    	    }
				    	    
				    	    if(datos.length == 6) {
				    	    	int id_carrera = Integer.parseInt(carrera);
								String clave = datos[0];
					    	    String nombre = datos[1];
					            int semestre = Integer.parseInt(datos[2]);
					            int hrs_t = Integer.parseInt(datos[3]);
					            int hrs_p = Integer.parseInt(datos[4]);
					            int creditos = Integer.parseInt(datos[5]);
					    			
					            MateriaBean m = new MateriaBean(); 
					            m.setId_carrera(id_carrera);
					            m.setClave_materia(clave);
					            m.setNombre(nombre);  
					            m.setSemestre(semestre);
					            m.setHrs_teoria(hrs_t);
					            m.setHrs_practica(hrs_p);
					            m.setCreditos(creditos);
					            
					            int status = MateriaDAO.save(m); 
				    	    }

				    	 }
				    	 
				    	 response.sendRedirect(request.getContextPath() + "/materias.jsp");			           
					} 
				}
	         
	     } catch(Exception ex) {
	        System.out.println(ex);
	     }
	      
	      
			
			
	}
	
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	      if (cd.trim().startsWith("filename")) {
	        String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	        return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	      }
	    }
	    return null;
	  }

}
