<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.UsuarioBean, javax.servlet.http.HttpSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Inicio-TECNM</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="vistas/assets/css/style.css">
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
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<div class="col-12" style="text-align: center;">
			<h2>Bienvenido ${nombre}</h2>
			<img src="vistas/assets/img/logo_tec.png">
		</div>
		
	</div>
	<%} %>

</html>