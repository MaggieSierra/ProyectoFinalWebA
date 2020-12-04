<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.CarreraBean, ModeloDAO.CarreraDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
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
				List<CarreraBean> list = CarreraDAO.getAllCarreras(); 
				request.setAttribute("carreras",list);
				String carrera_trabajar = (String) session.getAttribute("carrera_trabajar");
		
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<h2>Agregar Materia</h2>
		<form action="CrearMateriaServlet" method="post" style="margin-top:20px;">
			<table>
				<tr>
					<td>Clave Materia:</td>
					<td><input type="text" name="clave_materia" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Semestre:</td>
					<td><input type="number" name="semestre" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Hrs. Teoricas:</td>
					<td><input type="number" name="hrs_t" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Hrs. Prácticas:</td>
					<td><input type="number" name="hrs_p" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Créditos:</td>
					<td><input type="number" name="creditos" class="form-control" required /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="id_carrera" value="<%=carrera_trabajar%>" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" class="btn btn-success" value="Guardar"/>
					</td>
				</tr>
			</table>
		</form>
		<br/>
	</div>
	<%} }%>
</body>
</html>