<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ModeloDAO.MateriaDAO,ModeloDAO.HorarioDAO,ModeloDAO.CarreraDAO,ModeloDAO.UsuarioDAO,Modelo.HorarioBean,
Modelo.MateriaBean,Modelo.UsuarioBean, Modelo.CarreraBean"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sabana - Tecnm </title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<%
	HttpSession session_user=request.getSession();    
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
			List<UsuarioBean> list_usuarios =  UsuarioDAO.getAllUsuarios();
			CarreraBean carrera_bean = CarreraDAO.getCarreraById(Integer.parseInt(carrera_trabajar)); 
			List<MateriaBean> list_materias = MateriaDAO.getAllMaterias(carrera_trabajar);
			
			request.setAttribute("usuarios", list_usuarios);
			request.setAttribute("carrera", carrera_bean);
			request.setAttribute("materias", list_materias);
			
			String[] hora_materia = {"", "07:00-08:40", "08:45-10:25", "10:30-12:10", "12:15-13:55", "14:05-15:45", "15:50-17:30", "17:35-19:15", "19:20-21:00"};
			request.setAttribute("hora_materia", hora_materia);
			String[] hora_materia_viernes = {"", "07:00-07:50", "07:50-08:40", "08:45-09:35", "09:35-10:25", "10:30-11:20", "11:20-12:10", "12:15-13:05",
					"13:05-13:55", "14:05-14:55", "14:55-15:45", "15:50-16:40","16:40-17:30", "17:35-18:25", "18:25-19:15", "19:20-20:10","20:10-21:00"};
			request.setAttribute("hora_materia_viernes", hora_materia_viernes);
		%>
	
		<h2>Agregar Horario</h2>
		<form action="CrearHorarioServlet" method="post" style="margin-top:20px;">
			<table>
				<tr>
					<td>Maestro:</td>
					<td><select name="id_usuario" class="form-control">
							<c:forEach items="${usuarios}" var="usuario">
								<option value="${usuario.getId_usuario()}">${usuario.getPrefijo()} ${usuario.getNombre()} ${usuario.getPrimer_apellido()} ${usuario.getSegundo_apellido()}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Materia:</td>
					<td><select name="id_materia" class="form-control" required>
							<c:forEach items="${materias}" var="materia">
								<option value="${materia.getId_materia()}">${materia.getNombre()}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Carrera:</td>
					<td>
						<input type="hidden" name="id_carrera" value="${carrera.getId_carrera()}">
						<input type="text" name="nombre_carrera" class="form-control" readonly value="${carrera.getNombre()}" />
					</td>
				</tr>
				<tr>
					<td>Periodo:</td>
					<td><input type="text" name="periodo" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Grupo:</td>
					<td><input type="text" name="grupo" class="form-control" required /></td>
				</tr>
				<tr>
					<td>No. de alumnos:</td>
					<td><input type="number" name="alumnos" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Lunes:</td>
					<td>
						 <select class="form-control"  name="lunes">
							<c:forEach items="${hora_materia}" var="hora">
								<option value="${hora}">${hora}</option>
							</c:forEach>
					    </select>
					</td>
				</tr>
				<tr>
					<td>Martes:</td>
					<td><select class="form-control"  name="martes">
							<c:forEach items="${hora_materia}" var="hora">
								<option value="${hora}">${hora}</option>
							</c:forEach>
					    </select>
					 </td>
				</tr>
				<tr>
					<td>Miercoles:</td>
					<td><select class="form-control"  name="miercoles">
							<c:forEach items="${hora_materia}" var="hora">
								<option value="${hora}">${hora}</option>
							</c:forEach>
					    </select>
					</td>
				</tr>
				<tr>
					<td>Jueves:</td>
					<td><select class="form-control"  name="jueves">
							<c:forEach items="${hora_materia}" var="hora">
								<option value="${hora}">${hora}</option>
							</c:forEach>
					    </select></td>
				</tr>
				<tr>
					<td>Viernes:</td>
					<td><select class="form-control"  name="viernes">
							<c:forEach items="${hora_materia_viernes}" var="hora">
								<option value="${hora}">${hora}</option>
							</c:forEach>
					    </select>
				    </td>
				</tr>
				<tr>
					<td>Aula:</td>
					<td><input type="text" name="aula" class="form-control" placeholder="C-D1" required /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit" class="btn btn-success" value="Guardar"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%} }%>
</body>
	
</html>