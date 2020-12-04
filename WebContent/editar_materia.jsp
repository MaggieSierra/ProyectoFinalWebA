<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.MateriaBean, ModeloDAO.MateriaDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
<%@ page import="Modelo.CarreraBean, ModeloDAO.CarreraDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Materias - Tecnm</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<%	HttpSession session_user=request.getSession();    
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
				
				String sid=request.getParameter("id");  
	            int id=Integer.parseInt(sid);  
	 
	            MateriaBean m= MateriaDAO.getMateriaById(id); 
	            request.setAttribute("m",m);
	            
	            List<CarreraBean> list = CarreraDAO.getAllCarreras(); 
				request.setAttribute("carreras",list);
				
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<h2>Editar Materia</h2>
		<form action="UpdateMateriaServlet" method="post" style="margin-top:20px;">
			<table>
				<tr>
					<td><input type='hidden' name='id' class='form-control' required value='${m.getId_materia()}'/>
						<input type="hidden" name="id_carrera" value="<%=m.getId_carrera()%>"/></td>
				</tr>
				<tr>
					<td>Clave Materia:</td>
					<td><input type="text" name="clave_materia" class="form-control" required value='${m.getClave_materia()}' /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" class="form-control" required value='${m.getNombre()}' /></td>
				</tr>
				<tr>
					<td>Semestre:</td>
					<td><input type="number" name="semestre" class="form-control" required value='${m.getSemestre()}'/></td>
				</tr>
				<tr>
					<td>Hrs. Teoricas:</td>
					<td><input type="number" name="hrs_t" class="form-control" required value='${m.getHrs_teoria()}' /></td>
				</tr>
				<tr>
					<td>Hrs. Prácticas:</td>
					<td><input type="number" name="hrs_p" class="form-control" required value='${m.getHrs_practica()}'/></td>
				</tr>
				<tr>
					<td>Créditos:</td>
					<td><input type="number" name="creditos" class="form-control" required value='${m.getCreditos()}'/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" class="btn btn-warning" value="Actualizar"/>
					</td>
				</tr>
			</table>
		</form>
		<br/>
	</div>
	<%}} %>
</body>
</html>