<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.HorarioBean, ModeloDAO.HorarioDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sabana - Tecnm</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<%	HttpSession session_user=request.getSession();    
		if (session_user.getAttribute("usuario") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}else{
			String carrera_trabajar = (String) session.getAttribute("carrera_trabajar");
			String user = (String)session_user.getAttribute("usuario");
			int rol = (int)session_user.getAttribute("rol");
			if(rol != 2){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}%><div class="container">
			<jsp:include page="menu.jsp" />
			<br><%
			if(carrera_trabajar != "" && carrera_trabajar != null){
				String busqueda = request.getParameter("txtBuscar");
				
				if(busqueda != null){
					List<HorarioBean> list = HorarioDAO.searchHorario(busqueda, carrera_trabajar); 
					request.setAttribute("list",list);
				}else{
					List<HorarioBean> list = HorarioDAO.getAllHorarios(carrera_trabajar); 
					request.setAttribute("list",list);
				} 
		
	%>
		<div class='navbar'>
			<h2>Sabana</h2>
			<button class='btn btn-success' style='margin-right:10px;'>
					<a style="text-decoration: none; color:white;" href="crear_horario.jsp">Agregar Registro</a>
			</button>
	        <form class='form-inline' action='sabana.jsp' method='get'> 
	        	<input type='search' name='txtBuscar' class='form-control' style='margin-right:15px;'> 
	        	<input type='submit' name='buscar' class='btn btn-outline-primary' value='Buscar'> 
	        </form>
	    </div>
		<table border='1' width='100%' class='table'>  
		<thead class='thead-dark'>
			<tr>
				<th>Clave Horario</th>
				<th>Clave Materia</th>
				<th>Clave Carrera</th>
				<th>Materia</th>
				<th>Carrera</th>
				<th>Maestro</th>
				<th>Periodo</th>
				<th>Turno</th>
				<th><p class="verticalText">Grupo</p></th>
				<th><p class="verticalText">Alumnos</p></th>
				<th><p class="verticalText">Semestre</p></th>
				<th></th>
	            <th></th>
	            <th></th>
	         </tr>
         </thead>
         <tbody>
         <c:if test="${list.size() == 0}">
			<tr><td colspan='19'>No se encontraron registros</td></tr>
		</c:if>
		<c:forEach var="sabana" items="${list}">
			<tr>
				<td>${sabana.getId_horario()}</td>
				<td>${sabana.getClave_materia()}</td>
				<td>${sabana.getClave_carrera()}</td>
				<td>${sabana.getNombre_materia()}</td>
				<td>${sabana.getNombre_carrera()}</td>
				<td>${sabana.getPrefijo()} ${sabana.getPrimer_apellido()} ${sabana.getSegundo_apellido()} ${sabana.getNombre_usuario()}</td>
				<td>${sabana.getPeriodo()}</td>
				<td>${sabana.getTurno()}</td>
				<td>${sabana.getGrupo()}</td>
				<td>${sabana.getNum_alumnos()}</td>
				<td>${sabana.getSemestre()}</td>
				<td><button class='btn btn-primary' style='margin-right:10px;'>
						<a style='text-decoration: none; color:white;' href="detalle_horario.jsp?id=${sabana.getId_horario()}">Ver</a>
					</button></td>
				<td>
				<button class='btn btn-warning' style='margin-right:10px;'>
						<a style='text-decoration: none; color:white;' href="editar_horario.jsp?id=${sabana.getId_horario()}">Editar</a>
					</button>
	             	
	             </td>
	             <td><button class='btn btn-danger'>
	             		<a style='text-decoration: none; color:white;' href="DeleteHorarioServlet?id=${sabana.getId_horario()}">Eliminar</a>
             		</button></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<%} else{
		%><h4>Elija la carrera con la que quiere trabajar en la p�gina de inicio.</h4><%
	}
	}%>
</body>
</html>