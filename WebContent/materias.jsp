<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.MateriaBean, ModeloDAO.MateriaDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
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
			String carrera_trabajar = (String) session.getAttribute("carrera_trabajar");
			
			if(rol != 2){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		 %><div class="container">
			<jsp:include page="menu.jsp" />
			<br><%
			if(carrera_trabajar != "" && carrera_trabajar != null){
				String busqueda = request.getParameter("txtBuscar");
				
				if(busqueda != null){
					List<MateriaBean> list = MateriaDAO.searchMateria(busqueda, carrera_trabajar); 
					request.setAttribute("list",list);
				}else{
					List<MateriaBean> list = MateriaDAO.getAllMaterias(carrera_trabajar); 
					request.setAttribute("list",list);
				} 	
		
	%>
		<div class='navbar'>
			<h2>Lista de Materias</h2>
			<button class='btn btn-success' style='margin-right:10px;'>
					<a style="text-decoration: none; color:white;" href="crear_materia.jsp">Nueva Materia</a>
			</button>
	        <form class='form-inline' action='materias.jsp' method='get'> 
	        	<input type='search' name='txtBuscar' class='form-control' style='margin-right:15px;' autocomplete="off"> 
	        	<input type='submit' name='buscar' class='btn btn-outline-primary' value='Buscar'> 
	        </form>
	    </div>
		<table border='1' width='100%' class='table'>  
		<thead class='thead-dark'>
			<tr>
				<th>No.</th>
				<th>Clave Materia</th>
				<th>Nombre</th>
				<th>Semestre</th>
				<th>Horas<br>Teoricas</th>
	            <th>Horas<br>Prácticas</th>
	            <th>Créditos</th>
	            <th>Carrera</th>
	            <th></th>
	         </tr>
         </thead>
         <tbody>
         <c:if test="${list.size() == 0}">
			<tr><td colspan='9'>No se encontraron materias</td></tr>
		</c:if>
		<c:forEach var="materia" items="${list}" varStatus="count">
			<tr>
				<td>${count.index+1}</td>
				<td>${materia.getClave_materia()}</td>
				<td>${materia.getNombre()}</td>
				<td>${materia.getSemestre()}</td>
				<td>${materia.getHrs_teoria()}</td>
				<td>${materia.getHrs_practica()}</td>
				<td>${materia.getCreditos()}</td>
				<td>${materia.getCarrera()}</td>
				<td><button class='btn btn-warning' style='margin-right:10px;'>
						<a style='text-decoration: none; color:white;' href="editar_materia.jsp?id=${materia.getId_materia()}">Editar</a>
					</button>
	             	<button class='btn btn-danger'>
	             		<a style='text-decoration: none; color:white;' href="DeleteMateriaServlet?id=${materia.getId_materia()}">Eliminar</a>
             		</button>
	             </td>
			</tr>
		</c:forEach>
	</table>
	
	<%}else{
		%><h4>Elija la carrera con la que quiere trabajar en la página de inicio.</h4><%
	}
	}%>
	</div>
</body>
</html>