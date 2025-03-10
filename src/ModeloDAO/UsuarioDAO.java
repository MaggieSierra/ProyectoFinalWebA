package ModeloDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.CarreraBean;
import Modelo.UsuarioBean;
import Controlador.Conexion;

public class UsuarioDAO {  
  
	public static boolean validate(UsuarioBean bean){  
		boolean status=false;  
		
		try{  
			Connection con = Conexion.getConnection();;  
		              
			PreparedStatement ps=con.prepareStatement("select * from usuario where usuario = ? and contraseña = ?");  
			ps.setString(1,bean.getUsuario());  
			ps.setString(2, bean.getContraseña());  
		              
			ResultSet rs=ps.executeQuery();  
			status=rs.next();  
			
		              
		}catch(Exception e){}  
		  
		return status;   
	}  
	
	public static UsuarioBean getUsuario(UsuarioBean bean) {
		UsuarioBean user = new UsuarioBean();
		String sql = "select id_usuario, id_rol, prefijo, nombre, primer_apellido, segundo_apellido, usuario from usuario where usuario=? and contraseña=?";		
		
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bean.getUsuario());
			ps.setString(2, bean.getContraseña());

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setId_usuario(rs.getInt(1));
				user.setId_rol(rs.getInt(2));
				user.setPrefijo(rs.getString(3));
				user.setNombre(rs.getString(4));
				user.setPrimer_apellido(rs.getString(5));
				user.setSegundo_apellido(rs.getString(6));
				user.setUsuario(rs.getString(7));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}
	
	public static UsuarioBean getUsuarioById(int id) {
		UsuarioBean u = new UsuarioBean();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  id_usuario, usuario.id_rol, usuario.id_departamento, clave_usuario, prefijo, nombre," + 
					" primer_apellido, segundo_apellido, correo, telefono, hrs_trabajo, nombre_departamento, nombre_rol FROM usuario" + 
					" INNER JOIN departamento ON usuario.id_departamento = departamento.id_departamento " + 
					" INNER JOIN rol ON usuario.id_rol = rol.id_rol WHERE id_usuario = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setId_usuario(rs.getInt(1));
				u.setId_rol(rs.getInt(2));
				u.setId_departamento(rs.getInt(3));
				u.setClave_usuario(rs.getString(4));
				u.setPrefijo(rs.getString(5));
				u.setNombre(rs.getString(6));
				u.setPrimer_apellido(rs.getString(7));
				u.setSegundo_apellido(rs.getString(8));
				u.setCorreo(rs.getString(9));
				u.setTelefono(rs.getString(10));
				u.setHrs_trabajo(rs.getInt(11));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return u;
	}
	
	public static List<UsuarioBean> getAllUsuarios() {
		List<UsuarioBean> list = new ArrayList<UsuarioBean>();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  id_usuario, usuario.id_rol, usuario.id_departamento, clave_usuario, prefijo, nombre," + 
					" primer_apellido, segundo_apellido, correo, telefono, hrs_trabajo, nombre_departamento, nombre_rol FROM usuario"
					+ " INNER JOIN departamento ON usuario.id_departamento = departamento.id_departamento "
					+ " INNER JOIN rol ON usuario.id_rol = rol.id_rol WHERE rol.nombre_rol = 'Docente' OR rol.nombre_rol = 'Jefe Carrera'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioBean u = new UsuarioBean();
				u.setId_usuario(rs.getInt(1));
				u.setId_rol(rs.getInt(2));
				u.setId_departamento(rs.getInt(3));
				u.setClave_usuario(rs.getString(4));
				u.setPrefijo(rs.getString(5));
				u.setNombre(rs.getString(6));
				u.setPrimer_apellido(rs.getString(7));
				u.setSegundo_apellido(rs.getString(8));
				u.setCorreo(rs.getString(9));
				u.setTelefono(rs.getString(10));
				u.setHrs_trabajo(rs.getInt(11));
				u.setNombre_departamento(rs.getString(12));
				u.setNombre_rol(rs.getString(13));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static List<UsuarioBean> searchUsuario(String texto) {
		List<UsuarioBean> list = new ArrayList<UsuarioBean>();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  id_usuario, usuario.id_rol, usuario.id_departamento, clave_usuario, prefijo, nombre," + 
					" primer_apellido, segundo_apellido, correo, telefono, hrs_trabajo, nombre_departamento, nombre_rol FROM usuario"
					+ " INNER JOIN departamento ON usuario.id_departamento = departamento.id_departamento "
					+ " INNER JOIN rol ON usuario.id_rol = rol.id_rol WHERE clave_usuario LIKE ? OR nombre LIKE ? OR nombre_departamento LIKE ? AND  (rol.nombre_rol = 'Docente' OR rol.nombre_rol = 'Jefe Carrera')");
			ps.setString(1, "%"+texto+"%");
			ps.setString(2, "%"+texto+"%");
			ps.setString(3, "%"+texto+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioBean u = new UsuarioBean();
				u.setId_usuario(rs.getInt(1));
				u.setId_rol(rs.getInt(2));
				u.setId_departamento(rs.getInt(3));
				u.setClave_usuario(rs.getString(4));
				u.setPrefijo(rs.getString(5));
				u.setNombre(rs.getString(6));
				u.setPrimer_apellido(rs.getString(7));
				u.setSegundo_apellido(rs.getString(8));
				u.setCorreo(rs.getString(9));
				u.setTelefono(rs.getString(10));
				u.setHrs_trabajo(rs.getInt(11));
				u.setNombre_departamento(rs.getString(12));
				u.setNombre_rol(rs.getString(13));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	 public static List<CarreraBean> getCarrerasByIdUsuario(int id_usuario) {
		List<CarreraBean> list = new ArrayList<CarreraBean>();
		
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT carrera.id_carrera, carrera.nombre FROM usuario_carrera "
					+ "INNER JOIN carrera ON carrera.id_carrera = usuario_carrera.id_carrera WHERE id_usuario = ? ");
			ps.setInt(1, id_usuario);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CarreraBean bean = new CarreraBean();
				bean.setId_carrera(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return list;
	}
	 
	 public static List<UsuarioBean> getAllUserJefe() {
		List<UsuarioBean> list = new ArrayList<UsuarioBean>();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  usuario.id_usuario, usuario.id_rol, usuario.id_departamento, clave_usuario, prefijo," 
					+" usuario.nombre, primer_apellido, segundo_apellido, correo, telefono, hrs_trabajo, nombre_departamento, nombre_rol,"
					+ " carrera.nombre, jefe_carrera.id_jefe_carrera FROM usuario"
					+ " INNER JOIN departamento ON usuario.id_departamento = departamento.id_departamento "
					+ " INNER JOIN jefe_carrera ON jefe_carrera.id_usuario = usuario.id_usuario "
					+ " INNER JOIN carrera ON carrera.id_carrera = jefe_carrera.id_carrera "
					+ " INNER JOIN rol ON usuario.id_rol = rol.id_rol WHERE rol.nombre_rol = 'Jefe Carrera'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioBean u = new UsuarioBean();
				u.setId_usuario(rs.getInt(1));
				u.setId_rol(rs.getInt(2));
				u.setId_departamento(rs.getInt(3));
				u.setClave_usuario(rs.getString(4));
				u.setPrefijo(rs.getString(5));
				u.setNombre(rs.getString(6));
				u.setPrimer_apellido(rs.getString(7));
				u.setSegundo_apellido(rs.getString(8));
				u.setCorreo(rs.getString(9));
				u.setTelefono(rs.getString(10));
				u.setHrs_trabajo(rs.getInt(11));
				u.setNombre_departamento(rs.getString(12));
				u.setNombre_rol(rs.getString(13));
				u.setNombre_carrera(rs.getString(14));
				u.setId_jefe(rs.getInt(15));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	 
	 public static List<UsuarioBean> searchUserJefe(String texto) {
		List<UsuarioBean> list = new ArrayList<UsuarioBean>();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  id_usuario, usuario.id_rol, usuario.id_departamento, clave_usuario, prefijo, usuario.nombre," + 
					" primer_apellido, segundo_apellido, correo, telefono, hrs_trabajo, nombre_departamento, nombre_rol, carrera.nombre "
					+ " jefe_carrera.id_jefe_carrera FROM usuario"
					+ " INNER JOIN departamento ON usuario.id_departamento = departamento.id_departamento "
					+ " INNER JOIN jefe_carrera ON jefe_carrera.id_usuario = usuario.id_usuario "
					+ " INNER JOIN carrera ON carrera.id_carrera = jefe_carrera.id_carrera "
					+ " INNER JOIN rol ON usuario.id_rol = rol.id_rol WHERE clave_usuario LIKE ? OR nombre LIKE ? AND rol.nombre_rol = 'Jefe Carrera'");
			ps.setString(1, "%"+texto+"%");
			ps.setString(2, "%"+texto+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioBean u = new UsuarioBean();
				u.setId_usuario(rs.getInt(1));
				u.setId_rol(rs.getInt(2));
				u.setId_departamento(rs.getInt(3));
				u.setClave_usuario(rs.getString(4));
				u.setPrefijo(rs.getString(5));
				u.setNombre(rs.getString(6));
				u.setPrimer_apellido(rs.getString(7));
				u.setSegundo_apellido(rs.getString(8));
				u.setCorreo(rs.getString(9));
				u.setTelefono(rs.getString(10));
				u.setHrs_trabajo(rs.getInt(11));
				u.setNombre_departamento(rs.getString(12));
				u.setNombre_rol(rs.getString(13));
				u.setNombre_carrera(rs.getString(14));
				u.setId_jefe(rs.getInt(15));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	 
	 public static List<UsuarioBean> getAllUserByCarrera(String carrera_trabajar) {
		List<UsuarioBean> list = new ArrayList<UsuarioBean>();
		int id = Integer.parseInt(carrera_trabajar);
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  usuario.id_usuario, usuario.id_rol, usuario.id_departamento, clave_usuario, "
					+ " prefijo, usuario.nombre, primer_apellido, segundo_apellido, correo, telefono, hrs_trabajo, nombre_departamento, "
					+ " nombre_rol FROM usuario"
					+ " INNER JOIN departamento ON usuario.id_departamento = departamento.id_departamento "
					+ " INNER JOIN rol ON usuario.id_rol = rol.id_rol "
					+ " INNER JOIN usuario_carrera ON usuario_carrera.id_usuario = usuario.id_usuario "
					+ " INNER JOIN carrera ON carrera.id_carrera = usuario_carrera.id_carrera WHERE carrera.id_carrera = ? ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioBean u = new UsuarioBean();
				u.setId_usuario(rs.getInt(1));
				u.setId_rol(rs.getInt(2));
				u.setId_departamento(rs.getInt(3));
				u.setClave_usuario(rs.getString(4));
				u.setPrefijo(rs.getString(5));
				u.setNombre(rs.getString(6));
				u.setPrimer_apellido(rs.getString(7));
				u.setSegundo_apellido(rs.getString(8));
				u.setCorreo(rs.getString(9));
				u.setTelefono(rs.getString(10));
				u.setHrs_trabajo(rs.getInt(11));
				u.setNombre_departamento(rs.getString(12));
				u.setNombre_rol(rs.getString(13));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
}  