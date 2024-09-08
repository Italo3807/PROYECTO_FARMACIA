package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import model.Empleado;
import utilidades.Metodos_contraseña;

import java.sql.CallableStatement;

public class EmpleadoImpl implements EmpleadoInterface{
	Connection conex;
	Metodos_contraseña utilidades;
	public EmpleadoImpl() {
		this.conex=Conexion.getConnection();
		this.utilidades=new Metodos_contraseña();
	}
	
	@Override
	public Empleado login(String correo) {
		Empleado empleado = null;
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_loginEmpleado(?)}");
			sentencia.setString(1,correo);
			rs = sentencia.executeQuery();

			while (rs.next()) {
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt("idEmpleado"));
				empleado.setDniEmpleado(rs.getString("dniEmpleado"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApePaterno(rs.getString("apePaterno"));
				empleado.setApeMaterno(rs.getString("apeMaterno"));
				empleado.setFechaNacimiento(rs.getDate("fechaNacimiento"));
				empleado.setCorreo(rs.getString("correo"));
				empleado.setCelular(rs.getString("celular"));
				empleado.setSalt(rs.getString("salt"));
				empleado.setContrasenia(rs.getString("contrasenia"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleado;
	}

	@Override
	public int crearEmpleado(Empleado empleado) {
		String salt = utilidades.generadorSalt();
        String hashedPassword = utilidades.hashearContraseña(empleado.getContrasenia(), salt);
        int filasAlteradas=0;
        String sql = "INSERT INTO empleado (dniEmpleado, nombre, apePaterno, apeMaterno, fechaNacimiento, correo, celular, contrasenia, salt) "
        		+ "VALUES (?,?, ?, ?, ?,?,?, ?, ?)";
        try (
            PreparedStatement stmt = conex.prepareStatement(sql)) {
        	stmt.setString(1, empleado.getDniEmpleado());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getApePaterno());
            stmt.setString(4, empleado.getApeMaterno());
            stmt.setDate(5,(empleado.getFechaNacimiento()==null)?null: new Date(empleado.getFechaNacimiento().getTime()));
            stmt.setString(6, empleado.getCorreo());
            stmt.setString(7, empleado.getCelular());
            stmt.setString(8, hashedPassword);
            stmt.setString(9, salt);

            filasAlteradas= stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAlteradas;
	}

}
