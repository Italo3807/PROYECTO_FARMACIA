package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	static Connection conex=null;
	static String baseDatos="farmacia";
	static	String ruta="jdbc:mysql://localhost:3306/"+baseDatos;
	static	String usuario="root";
	static	String contraseña="mysql";
	public static Connection getConnection() {	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conex=DriverManager.getConnection(ruta,usuario,contraseña);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conex;
	}
	public static void cerrarConexion() {
		try {
			conex.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
