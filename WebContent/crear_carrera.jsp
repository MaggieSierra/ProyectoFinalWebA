<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Modelo.TurnoBean, ModeloDAO.TurnoDAO, java.util.*, java.sql.*, javax.servlet.http.HttpSession"%>
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
				List<TurnoBean> list_turno = TurnoDAO.getAllTurnos();
				request.setAttribute("turnos", list_turno);
			
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<h2>Agregar Carrera</h2>
		<form action="../CrearCarreraServlet" method="post" style="margin-top:20px;">
			<table>
				<tr>
					<td>Clave Carrera:</td>
					<td><input type="text" name="clave_carrera" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" class="form-control" required /></td>
				</tr>
				<tr>
					<td>Turno:</td>
					<td><select name="id_turno" class="form-control">
							<c:forEach items="${turnos}" var="turno">
								<option value="${turno.getId_turno()}">${turno.getTurno()}</option>
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