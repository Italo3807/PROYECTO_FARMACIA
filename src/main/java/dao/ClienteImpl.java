package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import model.Cliente;

public class ClienteImpl implements ClienteInterface {
	Connection conex;
	public ClienteImpl() {
		this.conex=Conexion.getConnection();
	}

	@Override
	public void insertarCliente(Cliente cliente) {
		ResultSet rs = null;

		CallableStatement sentencia;
		try {
			sentencia = conex.prepareCall("{call usp_crearCliente(?, ?, ?, ?, ?, ?, ?)}");
			sentencia.setString(1,cliente.getDniCliente());
			sentencia.setString(2,cliente.getNombre());
			sentencia.setString(3,cliente.getApePaterno());
			sentencia.setString(4,cliente.getApeMaterno());
			sentencia.setDate(5, new Date(cliente.getFechaNacimiento().getTime()));
			sentencia.setString(6,cliente.getCorreo());
			sentencia.setString(7,cliente.getCelular());
			rs = sentencia.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("insertarCliente ERRR" + e);
			e.printStackTrace();
		}

	}

	@Override
	public Cliente obtenerClientePorId(int idCliente) {
		Cliente cliente = new Cliente();
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_listarClienteById(?)}");
			sentencia.setInt(1,idCliente);
			rs = sentencia.executeQuery();

			while (rs.next()) {
				cliente.setIdCliente(rs.getInt("idCliente"));
		        cliente.setDniCliente(rs.getString("dniCliente"));
		        cliente.setNombre(rs.getString("nombre"));
		        cliente.setApePaterno(rs.getString("apePaterno"));
		        cliente.setApeMaterno(rs.getString("apeMaterno"));
		        cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
		        cliente.setCorreo(rs.getString("correo"));
		        cliente.setCelular(rs.getString("celular"));
			}
			
		} catch (SQLException e) {
			System.out.println("ERRRROR");
			e.printStackTrace();
		}

		return cliente;
	}

	@Override
	public ArrayList<Cliente> obtenerTodosLosClientes(String filtro) {
		ArrayList<Cliente> listCliente = new ArrayList<Cliente>();
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_listarCliente(?)}");
			sentencia.setString(1,filtro);
			rs = sentencia.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("idCliente"));
		        cliente.setDniCliente(rs.getString("dniCliente"));
		        cliente.setNombre(rs.getString("nombre"));
		        cliente.setApePaterno(rs.getString("apePaterno"));
		        cliente.setApeMaterno(rs.getString("apeMaterno"));
		        cliente.setFechaNacimiento(rs.getDate("fechaNacimiento"));
		        cliente.setCorreo(rs.getString("correo"));
		        cliente.setCelular(rs.getString("celular"));
		        listCliente.add(cliente);
			}
			
		} catch (SQLException e) {
			System.out.println("ERRRROR");
			e.printStackTrace();
		}

		return listCliente;
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_modificarCliente(?, ?, ?, ?, ?, ?, ?,?)}");
			sentencia.setString(1,cliente.getDniCliente());
			sentencia.setString(2,cliente.getNombre());
			sentencia.setString(3,cliente.getApePaterno());
			sentencia.setString(4,cliente.getApeMaterno());
			sentencia.setDate(5, new Date(cliente.getFechaNacimiento().getTime()));
			sentencia.setString(6,cliente.getCorreo());
			sentencia.setString(7,cliente.getCelular());
			sentencia.setInt(8,cliente.getIdCliente());
			rs = sentencia.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Modificar Cliente ERRR" + e);
			e.printStackTrace();
		}

	}

	@Override
	public void eliminarCliente(int idCliente) {
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_eliminarCliente(?)}");
			sentencia.setInt(1,idCliente);
			rs = sentencia.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("ERRRROR");
			e.printStackTrace();
		}

	}

}
