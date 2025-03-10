package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Connection connection;
	
	private Conexion() {
		String db_url = "jdbc:mysql://db:3306/proyecto_tec_mvc";
        String db_driver = "com.mysql.cj.jdbc.Driver";
        String db_user = "root";
        String db_password = "root";
        
        try {
            Class.forName( db_driver );
            connection = DriverManager.getConnection( db_url, db_user, db_password );
        } catch( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }   
	}
	
	public static Connection getConnection() {
        if(  connection == null )
            new Conexion();
        return connection;
    }
	
}