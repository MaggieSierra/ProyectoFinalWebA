package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Controlador.Conexion;
import Modelo.HorarioBean;

public class HorarioDAO {
	
	public static int save(HorarioBean bean) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			String sql = "INSERT INTO horario(id_usuario, id_materia, periodo, grupo, num_alumnos, aula, lunes,"
					+ "martes, miercoles, jueves, viernes) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,bean.getId_usuario());
			ps.setInt(2, bean.getId_materia());
			ps.setString(3, bean.getPeriodo());
			ps.setString(4,bean.getGrupo());
			ps.setInt(5,bean.getNum_alumnos());
			ps.setString(6,bean.getAula());
			ps.setString(7, bean.getLunes());
			ps.setString(8,bean.getMartes());
			ps.setString(9,bean.getMiercoles());
			ps.setString(10,bean.getJueves());
			ps.setString(11,bean.getViernes());

			status = ps.executeUpdate();
			
			int hrs = 0, creditos = 0;
			sql = "select creditos from materia where id_materia = ?";
			PreparedStatement ps2 = con.prepareStatement(sql);
			ps2.setInt(1, bean.getId_materia());
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				creditos = rs.getInt(1);
			}
			
			sql = "select hrs_trabajo from usuario where id_usuario = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1, bean.getId_usuario());
			rs = ps2.executeQuery();
			if (rs.next()) {
				hrs += rs.getInt(1);
			}
			
			hrs = hrs + creditos;
			
			//actualizar horas asignadas
			sql = "update usuario set hrs_trabajo = ? where id_usuario = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1,hrs);
			ps2.setInt(2,bean.getId_usuario());
			ps2.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public static int update(HorarioBean h) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			//actualizar  hrs de trabajo asignadas
			int hrs = 0, nuevas_hrs = 0, creditosM = 0, creditos=0, id_user=0;
			String sql = "select materia.creditos from horario inner join materia on "
					+ "horario.id_materia = materia.id_materia where id_horario = ?";
			PreparedStatement ps2 = con.prepareStatement(sql);
			ps2.setInt(1, h.getId_horario());
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				creditosM = rs.getInt(1);
			}
			
			sql = "select usuario.id_usuario, usuario.hrs_trabajo from horario inner join usuario on horario.id_usuario = usuario.id_usuario"
					+ " where id_horario = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1, h.getId_horario());
			rs = ps2.executeQuery();
			if (rs.next()) {
				id_user = rs.getInt(1);
				hrs = rs.getInt(2);
			}
			
			if(hrs != 0) {
				hrs = hrs - creditosM;
				sql = "update usuario set hrs_trabajo = ? where id_usuario = ?";
				ps2 = con.prepareStatement(sql);
				ps2.setInt(1,hrs);
				ps2.setInt(2,id_user);
				ps2.executeUpdate();
			}
			
			sql = "update horario set id_usuario = ?, id_materia = ?, id_carrera = ?, clave_horario = ?, periodo = ?, "
					+ "grupo = ?, num_alumnos = ?, aula = ?, lunes = ?, martes = ?, miercoles = ?, jueves = ?, viernes = ?"
					+ " where id_horario = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,h.getId_usuario());
			ps.setInt(2, h.getId_materia());
			ps.setInt(3, h.getId_carrera());
			ps.setString(4, h.getPeriodo());
			ps.setString(5,h.getGrupo());
			ps.setInt(6,h.getNum_alumnos());
			ps.setString(7,h.getAula());
			ps.setString(8, h.getLunes());
			ps.setString(9,h.getMartes());
			ps.setString(10,h.getMiercoles());
			ps.setString(11,h.getJueves());
			ps.setString(12,h.getViernes());
			ps.setInt(13,h.getId_horario());

			status = ps.executeUpdate();
			
			sql = "select creditos from materia where id_materia = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1, h.getId_materia());
			rs = ps2.executeQuery();
			if (rs.next()) {
				creditos = rs.getInt(1);
			}
			
			sql = "select hrs_trabajo from usuario where id_usuario = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1, h.getId_usuario());
			rs = ps2.executeQuery();
			if (rs.next()) {
				nuevas_hrs = rs.getInt(1);
			}
			
			nuevas_hrs = nuevas_hrs + creditos;
			
			//actualizar horas asignadas
			sql = "update usuario set hrs_trabajo = ? where id_usuario = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1,nuevas_hrs);
			ps2.setInt(2,h.getId_usuario());
			ps2.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	
	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = Conexion.getConnection();
			//actualizar horas asignadas
			int hrs = 0, creditos = 0, id_user= 0;
			String sql = "select materia.creditos from horario inner join materia on "
					+ "horario.id_materia = materia.id_materia where id_horario = ?";
			PreparedStatement ps2 = con.prepareStatement(sql);
			ps2.setInt(1, id);
			ps2.executeQuery();
			ResultSet rs = ps2.executeQuery();
			if (rs.next()) {
				creditos = rs.getInt(1);
			}
			
			sql = "select usuario.id_usuario, usuario.hrs_trabajo from horario inner join usuario on horario.id_usuario = usuario.id_usuario"
					+ " where id_horario = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1, id);
			rs = ps2.executeQuery();
			if (rs.next()) {
				id_user = rs.getInt(1);
				hrs += rs.getInt(2);
			}
			
			if(hrs != 0) {
				hrs = hrs - creditos;
			}else {
				hrs = creditos;
			}
			
			sql = "update usuario set hrs_trabajo = ? where id_usuario = ?";
			ps2 = con.prepareStatement(sql);
			ps2.setInt(1,hrs);
			ps2.setInt(2,id_user);
			ps2.executeUpdate();
			
			//eliminar registro
			PreparedStatement ps = con.prepareStatement("delete from horario where id_horario = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
	
	public static HorarioBean getHorarioById(int id) {
		HorarioBean h = new HorarioBean();
		try {
			Connection con = Conexion.getConnection();
			String sql = "SELECT horario.*, usuario.nombre, usuario.prefijo, usuario.primer_apellido, usuario.segundo_apellido,"
					+ " materia.clave_materia, materia.nombre, materia.semestre, materia.hrs_teoria, materia.hrs_practica, materia.creditos,"
					+ " carrera.clave_carrera, carrera.nombre, turno.turno FROM horario "
					+ " JOIN usuario ON usuario.id_usuario = horario.id_usuario "
					+ " JOIN materia ON materia.id_materia = horario.id_materia "
					+ " JOIN carrera ON carrera.id_carrera = materia.id_carrera "
					+ " JOIN turno ON carrera.id_turno = turno.id_turno WHERE horario.id_horario = ? ORDER BY id_horario;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				h.setId_horario(rs.getInt(1));
				h.setId_usuario(rs.getInt(2));
				h.setId_materia(rs.getInt(3));
				h.setPeriodo(rs.getString(4));
				h.setGrupo(rs.getString(5));
				h.setNum_alumnos(rs.getInt(6));
				h.setAula(rs.getString(7));
				h.setLunes(rs.getString(8));
				h.setMartes(rs.getString(9));
				h.setMiercoles(rs.getString(10));
				h.setJueves(rs.getString(11));
				h.setViernes(rs.getString(12));
				h.setNombre_usuario(rs.getString(13));
				h.setPrefijo(rs.getString(14));
				h.setPrimer_apellido(rs.getString(15));
				
				if(rs.getString(16) == null) {
					h.setSegundo_apellido("");
				}else {
					h.setSegundo_apellido(rs.getString(16));
				}
				
				h.setClave_materia(rs.getString(17));
				h.setNombre_materia(rs.getString(18));
				h.setSemestre(rs.getInt(19));
				h.setHrs_t(rs.getInt(20));
				h.setHrs_p(rs.getInt(21));
				h.setCreditos(rs.getInt(22));
				h.setClave_carrera(rs.getString(23));
				h.setNombre_carrera(rs.getString(24));
				h.setTurno(rs.getString(25));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return h;
	}
	
	public static List<HorarioBean> getHorarioByIdUser(int id) {
		List<HorarioBean> list = new ArrayList<HorarioBean>();
		try {
			Connection con = Conexion.getConnection();
			String sql = "SELECT horario.*, usuario.nombre, usuario.prefijo, usuario.primer_apellido, usuario.segundo_apellido,"
					+ " materia.clave_materia,materia.nombre, materia.semestre, materia.hrs_teoria, materia.hrs_practica, materia.creditos,"
					+ " carrera.clave_carrera, carrera.nombre, turno.turno FROM horario "
					+ " JOIN usuario ON usuario.id_usuario = horario.id_usuario "
					+ " JOIN materia ON materia.id_materia = horario.id_materia "
					+ " JOIN carrera ON carrera.id_carrera = materia.id_carrera "
					+ " JOIN turno ON carrera.id_turno = turno.id_turno "
					+ " WHERE horario.id_usuario = ? ORDER BY id_horario;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HorarioBean h = new HorarioBean();
				h.setId_horario(rs.getInt(1));
				h.setId_usuario(rs.getInt(2));
				h.setId_materia(rs.getInt(3));
				h.setPeriodo(rs.getString(4));
				h.setGrupo(rs.getString(5));
				h.setNum_alumnos(rs.getInt(6));
				h.setAula(rs.getString(7));
				h.setLunes(rs.getString(8));
				h.setMartes(rs.getString(9));
				h.setMiercoles(rs.getString(10));
				h.setJueves(rs.getString(11));
				h.setViernes(rs.getString(12));
				h.setNombre_usuario(rs.getString(13));
				h.setPrefijo(rs.getString(14));
				h.setPrimer_apellido(rs.getString(15));
				
				if(rs.getString(16) == null) {
					h.setSegundo_apellido("");
				}else {
					h.setSegundo_apellido(rs.getString(16));
				}
				
				h.setClave_materia(rs.getString(17));
				h.setNombre_materia(rs.getString(18));
				h.setSemestre(rs.getInt(19));
				h.setHrs_t(rs.getInt(20));
				h.setHrs_p(rs.getInt(21));
				h.setCreditos(rs.getInt(22));
				h.setClave_carrera(rs.getString(23));
				h.setNombre_carrera(rs.getString(24));
				h.setTurno(rs.getString(25));
		
				list.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static List<HorarioBean> getAllHorarios(String carrera_trabajar) {
		int id = Integer.parseInt(carrera_trabajar);
		List<HorarioBean> list = new ArrayList<HorarioBean>();
		try {
			Connection con = Conexion.getConnection();
			String sql = "SELECT horario.*, usuario.nombre, usuario.prefijo, usuario.primer_apellido, usuario.segundo_apellido,"
					+ " materia.clave_materia,materia.nombre, materia.semestre, materia.hrs_teoria, materia.hrs_practica, materia.creditos,"
					+ "carrera.clave_carrera, carrera.nombre, turno.turno FROM horario "
					+ "JOIN usuario ON usuario.id_usuario = horario.id_usuario "
					+ "JOIN materia ON materia.id_materia = horario.id_materia "
					+ "JOIN carrera ON carrera.id_carrera = materia.id_carrera "
					+ " JOIN turno ON carrera.id_turno = turno.id_turno WHERE materia.id_carrera = ? ORDER BY id_horario;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HorarioBean h = new HorarioBean();
				h.setId_horario(rs.getInt(1));
				h.setId_usuario(rs.getInt(2));
				h.setId_materia(rs.getInt(3));
				h.setPeriodo(rs.getString(4));
				h.setGrupo(rs.getString(5));
				h.setNum_alumnos(rs.getInt(6));
				h.setAula(rs.getString(7));
				h.setLunes(rs.getString(8));
				h.setMartes(rs.getString(9));
				h.setMiercoles(rs.getString(10));
				h.setJueves(rs.getString(11));
				h.setViernes(rs.getString(12));
				h.setNombre_usuario(rs.getString(13));
				h.setPrefijo(rs.getString(14));
				h.setPrimer_apellido(rs.getString(15));
				
				if(rs.getString(16) == null) {
					h.setSegundo_apellido("");
				}else {
					h.setSegundo_apellido(rs.getString(16));
				}
				
				h.setClave_materia(rs.getString(17));
				h.setNombre_materia(rs.getString(18));
				h.setSemestre(rs.getInt(19));
				h.setHrs_t(rs.getInt(20));
				h.setHrs_p(rs.getInt(21));
				h.setCreditos(rs.getInt(22));
				h.setClave_carrera(rs.getString(23));
				h.setNombre_carrera(rs.getString(24));
				h.setTurno(rs.getString(25));
		
				list.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static List<HorarioBean> searchHorario(String texto, String carrera_trabajar) {
		List<HorarioBean> list = new ArrayList<HorarioBean>();
		int id = Integer.parseInt(carrera_trabajar);
		try {
			Connection con = Conexion.getConnection();
			String sql = "SELECT horario.*, usuario.nombre, usuario.prefijo, usuario.primer_apellido, usuario.segundo_apellido,"
					+ " materia.clave_materia,materia.nombre, materia.semestre, materia.hrs_teoria, materia.hrs_practica, materia.creditos,"
					+ "carrera.clave_carrera, carrera.nombre, turno.turno FROM horario "
					+ "JOIN usuario ON usuario.id_usuario = horario.id_usuario "
					+ "JOIN materia ON materia.id_materia = horario.id_materia "
					+ "JOIN carrera ON carrera.id_carrera = materia.id_carrera "
					+ "WHERE carrera.id_carrera = ? AND materia.clave_materia like ? OR materia.nombre like ? OR usuario.nombre like ? ORDER BY id_horario;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, "%"+texto+"%");
			ps.setString(3, "%"+texto+"%");
			ps.setString(4, "%"+texto+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HorarioBean h = new HorarioBean();
				h.setId_horario(rs.getInt(1));
				h.setId_usuario(rs.getInt(2));
				h.setId_materia(rs.getInt(3));
				h.setPeriodo(rs.getString(4));
				h.setGrupo(rs.getString(5));
				h.setNum_alumnos(rs.getInt(6));
				h.setAula(rs.getString(7));
				h.setLunes(rs.getString(8));
				h.setMartes(rs.getString(9));
				h.setMiercoles(rs.getString(10));
				h.setJueves(rs.getString(11));
				h.setViernes(rs.getString(12));
				h.setNombre_usuario(rs.getString(13));
				h.setPrefijo(rs.getString(14));
				h.setPrimer_apellido(rs.getString(15));
				
				if(rs.getString(16) == null) {
					h.setSegundo_apellido("");
				}else {
					h.setSegundo_apellido(rs.getString(16));
				}
				
				h.setClave_materia(rs.getString(17));
				h.setNombre_materia(rs.getString(18));
				h.setSemestre(rs.getInt(19));
				h.setHrs_t(rs.getInt(20));
				h.setHrs_p(rs.getInt(21));
				h.setCreditos(rs.getInt(22));
				h.setClave_carrera(rs.getString(23));
				h.setNombre_carrera(rs.getString(24));
				h.setTurno(rs.getString(25));
		
				list.add(h);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
}
