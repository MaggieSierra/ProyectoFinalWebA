<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ModeloDAO.UsuarioDAO,Modelo.UsuarioBean, ModeloDAO.DepartamentoDAO, javax.servlet.http.HttpSession"%>
<%@ page import="Modelo.DepartamentoBean, ModeloDAO.DepartamentoDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Inicio-TECNM</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<%	HttpSession session_user=request.getSession();    
		if (session_user.getAttribute("usuario") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}else{ 
			int id_usuario = (int)session_user.getAttribute("id_usuario");
			UsuarioBean us = UsuarioDAO.getUsuarioById(id_usuario);
			DepartamentoBean dep = DepartamentoDAO.getDepartamentoById(us.getId_departamento());
			request.setAttribute("usuario",us);
			request.setAttribute("departamento",dep);
			
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<div class="col-12" style="text-align: center;">
			<h2>Mi Perfil</h2>
		</div>
		<div class="col-12" style="display: flex; justify-content: center;">
		<table class="table-striped" id="">
			<tbody>
				<tr>
					<td style="width: 200px;"><strong>Nombre:</strong></td>
					<td>${usuario.getNombre()} ${usuario.getPrimer_apellido()} ${usuario.getSegundo_apellido()}</td>
				</tr>
				<tr>
					<td style="width: 200px;"><strong>Correo:</strong></td>
					<td>${usuario.getCorreo()} </td>
				</tr>
				<tr>
					<td style="width: 200px;"><strong>Telefono:</strong></td>
					<td>${usuario.getTelefono()}</td>
				</tr>
				<tr>
					<td style="width: 200px;"><strong>Departamento:</strong></td>
					<td>${departamento.getNombre_departamento()} </td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	<%} %>
</html>