package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Controlador.Conexion;
import Modelo.TurnoBean;

public class TurnoDAO {
	
	public static List<TurnoBean> getAllTurnos() {
		List<TurnoBean> list = new ArrayList<TurnoBean>();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from turno");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TurnoBean bean = new TurnoBean();
				bean.setId_turno(rs.getInt(1));
				bean.setTurno(rs.getString(2));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
