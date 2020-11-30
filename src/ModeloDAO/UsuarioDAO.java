package ModeloDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.UsuarioBean;  
import Controlador.Conexion;

public class UsuarioDAO {  
  
	public static boolean validate(UsuarioBean bean){  
		boolean status=false;  
		
		try{  
			Connection con = Conexion.getConnection();;  
		              
			PreparedStatement ps=con.prepareStatement("select * from usuarios where usuario = ? and contraseña = ?");  
			ps.setString(1,bean.getUsuario());  
			ps.setString(2, bean.getContraseña());  
		              
			ResultSet rs=ps.executeQuery();  
			status=rs.next();  
			
		              
		}catch(Exception e){}  
		  
		return status;   
	}  
	
	public static UsuarioBean getUsuario(UsuarioBean bean) {
		UsuarioBean user = new UsuarioBean();
		String sql = "select id_usuario, id_rol, prefijo, nombre, primer_apellido, segundo_apellido, usuario from usuarios where usuario=? and contraseña=?";		
		
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
			PreparedStatement ps = con.prepareStatement("select id_usuario, id_rol, id_departamento, clave_usuario, prefijo, nombre, "
					+ "primer_apellido, segundo_apellido, correo, telefono, hrs_trabajo from usuarios where id_usuario = ?");
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
			PreparedStatement ps = con.prepareStatement("select id_usuario, id_rol, clave_usuario, prefijo, nombre, primer_apellido, segundo_apellido, hrs_trabajo from usuarios");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioBean u = new UsuarioBean();
				u.setId_usuario(rs.getInt(1));
				u.setId_rol(rs.getInt(2));
				u.setClave_usuario(rs.getString(3));
				u.setPrefijo(rs.getString(4));
				u.setNombre(rs.getString(5));
				u.setPrimer_apellido(rs.getString(6));
				u.setSegundo_apellido(rs.getString(7));
				u.setHrs_trabajo(rs.getInt(8));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}  