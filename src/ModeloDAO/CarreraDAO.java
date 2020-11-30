package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Controlador.Conexion;
import Modelo.CarreraBean;

public class CarreraDAO {
	
	public static int save(CarreraBean bean) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into carreras(id_departamento, clave_carrera, nombre, turno) values (?,?,?,?)");
			ps.setInt(1, bean.getId_departamento());
			ps.setString(2, bean.getClave_carrera());
			ps.setString(3, bean.getNombre());
			ps.setString(4, bean.getTurno());

			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public static int update(CarreraBean bean) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("update carreras set id_departamentos = ?, clave_carrera = ?, nombre = ?, turno = ?"
					+ " where id_carrera = ?");
			ps.setInt(1, bean.getId_departamento());
			ps.setString(2, bean.getClave_carrera());
			ps.setString(3, bean.getNombre());
			ps.setString(4, bean.getTurno());
			ps.setInt(5, bean.getId_carrera());

			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public static CarreraBean getCarreraById(int id) {
		CarreraBean bean = new CarreraBean();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("select carreras.*, departamentos.nombre_departamento from carreras"
					+ " inner join departamentos on carreras.id_departamento = departamentos.id_departamento where id_carrera = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean.setId_carrera(rs.getInt(1));
				bean.setId_departamento(rs.getInt(2));
				bean.setClave_carrera(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setTurno(rs.getString(5));
				bean.setNombre_departamento(rs.getString(6));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return bean;
	}

	public static List<CarreraBean> getAllCarreras() {
		List<CarreraBean> list = new ArrayList<CarreraBean>();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("select carreras.*, departamentos.nombre_departamento from carreras"
					+ " inner join departamentos on carreras.id_departamento = departamentos.id_departamento");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CarreraBean bean = new CarreraBean();
				bean.setId_carrera(rs.getInt(1));
				bean.setId_departamento(rs.getInt(2));
				bean.setClave_carrera(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setTurno(rs.getString(5));
				bean.setNombre_departamento(rs.getString(6));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static List<CarreraBean> searchCarrera(String texto) {
		List<CarreraBean> list = new ArrayList<CarreraBean>();
		
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT carreras.*, departamentos.nombre_departamento FROM carreras"
					+ " INNER JOIN departamentos ON carreras.id_departamento = departamentos.id_departamento WHERE clave_carrera LIKE ?"
					+ "OR nombre LIKE ? OR nombre_departamento = ?");
			ps.setString(1, "%"+texto+"%");
			ps.setString(2, "%"+texto+"%");
			ps.setString(3, "%"+texto+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CarreraBean bean = new CarreraBean();
				bean.setId_carrera(rs.getInt(1));
				bean.setId_departamento(rs.getInt(2));
				bean.setClave_carrera(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setTurno(rs.getString(5));
				bean.setNombre_departamento(rs.getString(6));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
