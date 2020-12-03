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
			PreparedStatement ps = con.prepareStatement("insert into carrera(clave_carrera, nombre, id_turno) values (?,?,?)");
			ps.setString(1, bean.getClave_carrera());
			ps.setString(2, bean.getNombre());
			ps.setInt(3, bean.getIdTurno());

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
			PreparedStatement ps = con.prepareStatement("update carrera set clave_carrera = ?, nombre = ?, id_turno = ? where id_carrera = ?");
			ps.setString(1, bean.getClave_carrera());
			ps.setString(2, bean.getNombre());
			ps.setInt(3, bean.getIdTurno());
			ps.setInt(4, bean.getId_carrera());

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
			PreparedStatement ps = con.prepareStatement("select carrera.*, turno.turno from carrera"
					+ " inner join turno on carreara.id_turno = turno.id_turno where id_carrera = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean.setId_carrera(rs.getInt(1));
				bean.setIdTurno(rs.getInt(2));
				bean.setClave_carrera(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setTurno(rs.getString(5));
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
			PreparedStatement ps = con.prepareStatement("select carrera.*, turno.turno from carrera"
					+ " inner join turno on carrera.id_turno = turno.id_turno");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CarreraBean bean = new CarreraBean();
				bean.setId_carrera(rs.getInt(1));
				bean.setIdTurno(rs.getInt(2));
				bean.setClave_carrera(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setTurno(rs.getString(5));
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
			PreparedStatement ps = con.prepareStatement("SELECT carrera.*, turno.turno FROM carrera"
					+ " INNER JOIN turno ON carrera.id_turno = turno.id_turno WHERE clave_carrera LIKE ?"
					+ "OR nombre LIKE ? OR turno = ?");
			ps.setString(1, "%"+texto+"%");
			ps.setString(2, "%"+texto+"%");
			ps.setString(3, "%"+texto+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CarreraBean bean = new CarreraBean();
				bean.setId_carrera(rs.getInt(1));
				bean.setIdTurno(rs.getInt(2));
				bean.setClave_carrera(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setTurno(rs.getString(5));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
