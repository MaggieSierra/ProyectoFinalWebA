<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ModeloDAO.UsuarioDAO, Modelo.CarreraBean, javax.servlet.http.HttpSession, java.util.*"%>
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
			request.setAttribute("rol",rol);
	%>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<br>
		<div class="col-12" style="text-align: center;">
			<h2>Bienvenido ${nombre}</h2>
			<br>
		</div>
	<%
		if(rol == 2){
			List<CarreraBean> carreras = UsuarioDAO.getCarrerasByIdUsuario((int)session_user.getAttribute("id_usuario"));
			request.setAttribute("carreras", carreras);
			
			if (carreras.size() > 0) {
				String carrera_trabajar = request.getParameter("carrera_trabajar");
				if(carrera_trabajar==null && session_user.getAttribute("carrera_trabajar")!=null){
					session_user.setAttribute("carrera_trabajar", session_user.getAttribute("carrera_trabajar"));
				}else{
					session_user.setAttribute("carrera_trabajar", carrera_trabajar);
				}
				
				%>
				<div class="row">
					<div class="col-12" style="display: flex; justify-content: center;">
						<form action="index.jsp">
							<h4>Elija la carrera con la que quiere trabajar:</h4>
							<table>
								<tr>
									<td>
										<select name="carrera_trabajar" class="form-control">
											<c:forEach items="${carreras}" var="carrera">
												<option value="${carrera.getId_carrera()}">${carrera.getNombre()}</option>
											</c:forEach>
										</select>
									</td>
									<td><button type="submit" value="Elegir" class="btn btn-primary">Aceptar</button></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
		
		<%}
			
		}
	} %>
	<img src="assets/img/logo_tec.png">
	</div>

</html>