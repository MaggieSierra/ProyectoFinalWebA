<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.CarreraBean, ModeloDAO.CarreraDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
<%@ page import="Modelo.UsuarioBean, ModeloDAO.UsuarioDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
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
			if(rol != 1){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}else{
				List<CarreraBean> list_carrera = CarreraDAO.getAllCarreras(); 
				List<UsuarioBean> list_user = UsuarioDAO.getAllUsuarios(); 
				request.setAttribute("usuarios",list_user);
				request.setAttribute("carreras",list_carrera);
		
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<h2>Asignar Jefe de Carrera</h2>
		<form action="AsignarJefeServlet" method="post" style="margin-top:20px;">
			<table>
				<tr>
					<td style="width:150px;">Carrera :</td>
					<td style="width:350px;">
						<select name="id_carrera" class="form-control">
							<c:forEach items="${carreras}" var="carrera">
								<option value="${carrera.getId_carrera()}">${carrera.getNombre()}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Nombre del Jefe:</td>
					<td><select name="id_usuario" class="form-control">
							<c:forEach items="${usuarios}" var="usuario">
								<option value="${usuario.getId_usuario()}">${usuario.getNombre()} ${usuario.getPrimer_apellido()} ${usuario.getSegundo_apellido()}</option>
							</c:forEach>
						</select>
					</td>
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