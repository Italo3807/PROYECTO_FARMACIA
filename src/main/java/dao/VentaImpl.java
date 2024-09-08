package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.Conexion;
import model.DetalleVenta;
import model.Venta;

public class VentaImpl implements VentaInterface {

	Connection conex;
	public VentaImpl() {
		this.conex=Conexion.getConnection();
	}
	
	@Override
	public int crearVenta(Venta venta) {
		int cambios = 0;
        try {
            String consulta = "INSERT INTO venta (idCliente, idEmpleado, fecha) VALUES (?, ?, ?)";
            PreparedStatement statement = conex.prepareStatement(consulta);
            statement.setInt(1, venta.getIdCliente());
            statement.setInt(2, venta.getIdVendedor());
            statement.setDate(3, new Date(venta.getFecha().getTime()));
            cambios = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cambios;
	}
	@Override
	public int crearDetalleVenta(DetalleVenta detalleVenta) {
		int cambios = 0;
        try {
            String consulta = "INSERT INTO Detalle_venta (id_venta, idProducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement statement = conex.prepareStatement(consulta);
            statement.setInt(1, detalleVenta.getIdVenta());
            statement.setInt(2, detalleVenta.getProducto().getIdProducto());
            statement.setInt(3, detalleVenta.getCantidad());
            cambios = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cambios;
	}
	@Override
	public int traerUltimaVenta() {
		int idVenta = -1;
        try {
            String consulta = "SELECT max(id) as id FROM venta";
            PreparedStatement statement = conex.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	idVenta = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idVenta;
	}

}
