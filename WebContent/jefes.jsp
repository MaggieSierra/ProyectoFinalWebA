<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.UsuarioBean, ModeloDAO.UsuarioDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jefes de Carrera - Tecnm</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<%	HttpSession session_user=request.getSession();    
		if (session_user.getAttribute("usuario") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}else{
		
			int rol = (int)session_user.getAttribute("rol");
			if(rol != 1){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}else{
				String busqueda = request.getParameter("txtBuscar");
				
				if(busqueda != null){
					List<UsuarioBean> list = UsuarioDAO.searchUserJefe(busqueda); 
					request.setAttribute("list",list);
				}else{
					List<UsuarioBean> list = UsuarioDAO.getAllUserJefe(); 
					request.setAttribute("list",list);
				} 	
		
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<div class='navbar'>
			<h2>Jefes de Carrera</h2>
			<button class='btn btn-success' style='margin-right:10px;'>
					<a style="text-decoration: none; color:white;" href="asignar_jefe.jsp">Asignar Jefe</a>
			</button>
	        <form class='form-inline' action='jefes.jsp' method='get'> 
	        	<input type='search' name='txtBuscar' class='form-control' style='margin-right:15px;' autocomplete="off"> 
	        	<input type='submit' name='buscar' class='btn btn-outline-primary' value='Buscar'> 
	        </form>
	    </div>
		<table border='1' width='100%' class='table'>  
		<thead class='thead-dark'>
			<tr>
				<th>Clave Usuario</th>
				<th>Nombre</th>
				<th>Correo</th>
	            <th>Departamento</th>
	            <th>Carrera</th>
	            <th></th>
	         </tr>
         </thead>
         <tbody>
         <c:if test="${list.size() == 0}">
			<tr><td colspan='7'>No se encontraron jefes</td></tr>
		</c:if>
		<c:forEach var="usuario" items="${list}">
			<tr>
				<td>${usuario.getClave_usuario()}</td>
				<td>${usuario.getNombre()} ${usuario.getPrimer_apellido()} ${usuario.getSegundo_apellido()}</td>
				<td>${usuario.getCorreo()}</td>
				<td>${usuario.getNombre_departamento()}</td>
				<td>${usuario.getNombre_carrera()}</td>
				<td><button class='btn btn-warning' style='margin-right:10px;'>
						<a style='text-decoration: none; color:white;' href="QuitarJefeServlet?id=${usuario.getId_jefe()}">Quitar Jefe</a>
					</button>
	             </td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<%} }%>
</body>
</html>