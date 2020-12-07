<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.CarreraBean, ModeloDAO.CarreraDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carreras - Tecnm</title>
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
				String busqueda = request.getParameter("txtBuscar");
				
				if(busqueda != null){
					List<CarreraBean> list = CarreraDAO.searchCarrera(busqueda); 
					request.setAttribute("list",list);
				}else{
					List<CarreraBean> list = CarreraDAO.getAllCarreras(); 
					request.setAttribute("list",list);
				} 	
		
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<div class='navbar'>
			<h2>Lista de Carreras</h2>
			<button class='btn btn-success' style='margin-right:10px;'>
					<a style="text-decoration: none; color:white;" href="crear_carrera.jsp">Nueva Carrera</a>
			</button>
	        <form class='form-inline' action='materias.jsp' method='get'> 
	        	<input type='search' name='txtBuscar' class='form-control' style='margin-right:15px;' autocomplete="off"> 
	        	<input type='submit' name='buscar' class='btn btn-outline-primary' value='Buscar'> 
	        </form>
	    </div>
		<table border='1' width='100%' class='table'>  
		<thead class='thead-dark'>
			<tr>
				<th>Clave Carrera</th>
				<th>Nombre</th>
				<th>Turno</th>
	            <th></th>
	         </tr>
         </thead>
         <tbody>
         <c:if test="${list.size() == 0}">
			<tr><td colspan='9'>No se encontraron carreras</td></tr>
		</c:if>
		<c:forEach var="carrera" items="${list}">
			<tr>
				<td>${carrera.getClave_carrera()}</td>
				<td>${carrera.getNombre()}</td>
				<td>${carrera.getTurno()}</td>
				<td><button class='btn btn-warning' style='margin-right:10px;'>
						<a style='text-decoration: none; color:white;' href="asignar_carrera.jsp?id=${carrera.getId_carrera()}">Asignar Docente</a>
					</button>
	             </td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<%} }%>
</body>
</html>