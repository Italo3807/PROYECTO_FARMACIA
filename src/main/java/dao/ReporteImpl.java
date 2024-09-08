package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import model.Reporte;

public class ReporteImpl implements ReporteInterface {

	Connection conex;
	public ReporteImpl() {
		this.conex=Conexion.getConnection();
	}
	@Override
	public Reporte obtenerReporteEmpleados() {
		ArrayList<String>tags = new ArrayList<String>();
		ArrayList<Double> valores = new ArrayList<Double>();
		
        try {
            String consulta = " select  concat(e.nombre,\" \",e.apePaterno,\" \",e.apeMaterno) empleado ,sum(d.cantidad * p.precio) monto \r\n"
            		+ " from venta v\r\n"
            		+ " inner join detalle_venta d on v.id = d.id_venta\r\n"
            		+ " inner join producto p on d.idProducto = p.idProducto\r\n"
            		+ " inner join empleado e on v.idEmpleado = e.idEmpleado\r\n"
            		+ " group by e.idEmpleado\r\n"
            		+ " order by monto desc\r\n"
            		+ " limit 10";
            PreparedStatement statement = conex.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	tags.add(resultSet.getString("empleado"));
            	valores.add(resultSet.getDouble("monto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Reporte reporte = new Reporte();
        reporte.setTags(tags);
        reporte.setValores(valores);
        return reporte;
	}

	@Override
	public Reporte obtenerReporteProducto() {
		ArrayList<String>tags = new ArrayList<String>();
		ArrayList<Double> valores = new ArrayList<Double>();
		
        try {
            String consulta = "  select p.nombre,sum(d.cantidad * p.precio) monto from venta v\r\n"
            		+ " inner join detalle_venta d on v.id = d.id_venta\r\n"
            		+ " inner join producto p on d.idProducto = p.idProducto\r\n"
            		+ " group by p.idProducto\r\n"
            		+ " order by monto desc\r\n"
            		+ " limit 10";
            
            PreparedStatement statement = conex.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	tags.add(resultSet.getString("nombre"));
            	valores.add(resultSet.getDouble("monto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Reporte reporte = new Reporte();
        reporte.setTags(tags);
        reporte.setValores(valores);
        return reporte;
	}

}
