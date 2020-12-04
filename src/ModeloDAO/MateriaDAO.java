package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Modelo.MateriaBean;
import Controlador.Conexion;

public class MateriaDAO {
	
	public static int save(MateriaBean bean) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO materia(id_carrera, clave_materia, nombre, semestre,"
					+ "hrs_teoria, hrs_practica, creditos) VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1, bean.getId_carrera());
			ps.setString(2, bean.getClave_materia());
			ps.setString(3, bean.getNombre());
			ps.setInt(4, bean.getSemestre());
			ps.setInt(5, bean.getHrs_teoria());
			ps.setInt(6, bean.getHrs_practica());
			ps.setInt(7, bean.getCreditos());

			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public static int update(MateriaBean bean) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE materia SET id_carrera = ?, clave_materia = ?, nombre = ?, semestre = ?, "
					+ "hrs_teoria = ?, hrs_practica = ?, creditos = ? WHERE id_materia = ?");
			ps.setInt(1, bean.getId_carrera());
			ps.setString(2, bean.getClave_materia());
			ps.setString(3, bean.getNombre());
			ps.setInt(4, bean.getSemestre());
			ps.setInt(5, bean.getHrs_teoria());
			ps.setInt(6, bean.getHrs_practica());
			ps.setInt(7, bean.getCreditos());
			ps.setInt(8, bean.getId_materia());

			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM materia WHERE id_materia = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
	
	public static MateriaBean getMateriaById(int id) {
		MateriaBean bean = new MateriaBean();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM materia WHERE id_materia = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean.setId_materia(rs.getInt(1));
				bean.setId_carrera(rs.getInt(2));
				bean.setClave_materia(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setSemestre(rs.getInt(5));
				bean.setHrs_teoria(rs.getInt(6));
				bean.setHrs_practica(rs.getInt(7));
				bean.setCreditos(rs.getInt(8));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return bean;
	}

	public static List<MateriaBean> getAllMaterias(String carrera_trabajar) {
		List<MateriaBean> list = new ArrayList<MateriaBean>();
		int id = Integer.parseInt(carrera_trabajar);
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT materia.*, carrera.nombre FROM materia INNER JOIN carrera "
					+ "ON materia.id_carrera = carrera.id_carrera WHERE carrera.id_carrera = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MateriaBean bean = new MateriaBean();
				bean.setId_materia(rs.getInt(1));
				bean.setId_carrera(rs.getInt(2));
				bean.setClave_materia(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setSemestre(rs.getInt(5));
				bean.setHrs_teoria(rs.getInt(6));
				bean.setHrs_practica(rs.getInt(7));
				bean.setCreditos(rs.getInt(8));
				bean.setCarrera(rs.getString(9));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static List<MateriaBean> searchMateria(String texto, String carrera_trabajar) {
		List<MateriaBean> list = new ArrayList<MateriaBean>();
		int id = Integer.parseInt(carrera_trabajar);
		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT materia.*, carrera.nombre FROM materia INNER JOIN carrera" + 
					" ON materia.id_carrera = carrera.id_carrera WHERE clave_materia LIKE ? OR nombre LIKE ? AND carrera.id_carrera ? ");
			ps.setString(1, "%"+texto+"%");
			ps.setString(2, "%"+texto+"%");
			ps.setInt(3, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MateriaBean bean = new MateriaBean();
				bean.setId_materia(rs.getInt(1));
				bean.setId_carrera(rs.getInt(2));
				bean.setClave_materia(rs.getString(3));
				bean.setNombre(rs.getString(4));
				bean.setSemestre(rs.getInt(5));
				bean.setHrs_teoria(rs.getInt(6));
				bean.setHrs_practica(rs.getInt(7));
				bean.setCreditos(rs.getInt(8));
				bean.setCarrera(rs.getString(9));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
