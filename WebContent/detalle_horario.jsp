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
				
				int id = Integer.parseInt(request.getParameter("id"));
				HorarioBean list = HorarioDAO.getHorarioById(id); 
				request.setAttribute("sabana",list);

	%>
	<h4>Detalles del horario: ${sabana.getId_horario()} </h4>
	<h4>Docente: ${sabana.getPrefijo()} ${sabana.getPrimer_apellido()} ${sabana.getSegundo_apellido()} ${sabana.getNombre_usuario()}</h4>
	<table border='1' width='100%' class='table-striped'>  
		<tbody>
			<tr>
				<th>Clave Materia</th>
				<td>${sabana.getClave_materia()}</td>
				<th>Materia</th>
				<td>${sabana.getNombre_materia()}</td>
			</tr>
			<tr>
				<th>Clave Carrera</th>
				<td>${sabana.getClave_carrera()}</td>
				<th>Carrera</th>
				<td>${sabana.getNombre_carrera()}</td>
			</tr>
			<tr>
				<th>Periodo</th>
				<td>${sabana.getPeriodo()}</td>
				<th>Turno</th>
				<td>${sabana.getTurno()}</td>
			</tr>
			<tr>
				<th>Grupo</th>
				<td>${sabana.getGrupo()}</td>
				<th>No. Alumnos</th>
				<td>${sabana.getNum_alumnos()}</td>
			</tr>
			<tr>
				<th>Semestre</th>
				<td>${sabana.getSemestre()}</td>
				<td></td>
				<td></td>
	         </tr>
         </tbody>
	</table>
	<table border='1' width='100%' class='table-striped'>  
		<thead>
			<tr>
				<th>Lunes</th>
				<th>Martes</th>
				<th>Miercoles</th>
				<th>Jueves</th>
				<th>Viernes</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${sabana.getLunes()}</td>
				<td>${sabana.getMartes()}</td>
				<td>${sabana.getMiercoles()}</td>
				<td>${sabana.getJueves()}</td>
				<td>${sabana.getViernes()}</td>
			</tr>
         </tbody>
	</table>
	</div>
	<%} else{
		%><h4>Elija la carrera con la que quiere trabajar en la página de inicio.</h4><%
	}
	}%>
</body>
</html>