package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Controlador.Conexion;
import Modelo.DepartamentoBean;

public class DepartamentoDAO {
	
	public static DepartamentoBean getDepartamentoById(int id) {
		DepartamentoBean d = new DepartamentoBean();

		try {
			Connection con = Conexion.getConnection();
			PreparedStatement ps = con.prepareStatement("select id_departamento, nombre_departamento from departamento where id_departamento = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				d.setId_departamento(rs.getInt(1));
				d.setNombre_departamento(rs.getString(2));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return d;
	}
}
