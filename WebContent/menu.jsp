<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.UsuarioBean, javax.servlet.http.HttpSession"%>
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
			String user = (String)session_user.getAttribute("usuario");
			int rol = (int)session_user.getAttribute("rol");
			String nombre_completo = (String)session_user.getAttribute("prefijo")+" "+(String)session_user.getAttribute("nombre")+" "+(String)session_user.getAttribute("apellido");
			request.setAttribute("nombre",nombre_completo);
	%>
		<ul class='nav nav-tabs'>
			<li class='nav-item'><a class='nav-link' href='index.jsp'>Inicio</a></li>
			<%if (user != null && rol == 3) {%>
				<li class="nav-item"><a class="nav-link" href="reporte_docente.jsp">Reporte</a></li>
			<%}else if (user != null && rol == 2) {%>
				<li class="nav-item"><a class="nav-link" href="materias.jsp">Materias</a></li>
				<li class="nav-item"><a class="nav-link" href="sabana.jsp">Sabana de Materias</a></li>
				<li class="nav-item"><a class="nav-link" href="reportes.jsp">Reportes de Docentes</a></li>
			<%} else if(user != null && rol == 1){%>
				<li class="nav-item"><a class="nav-link" href="carreras.jsp">Carreras</a></li>
				<li class="nav-item"><a class="nav-link" href="docentes.jsp">Docentes</a></li>
				<li class="nav-item"><a class="nav-link" href="jefes.jsp">Jefes Carrera</a></li>
			<%}%>
			<li class="nav-item"><a class="nav-link" href="perfil.jsp">Mi Perfil</a></li>
			<li class="nav-item"><a class="nav-link" href="LogoutServlet">Cerrar Sesion</a></li>
		</ul>
	<%} %>

</html>