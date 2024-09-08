package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import model.Producto;

public class ProductoImpl implements ProductoInterface {
	Connection conex;
	public ProductoImpl() {
		this.conex=Conexion.getConnection();
	}
	
	@Override
	public void insertarProducto(Producto producto) {
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_crearProducto(?, ?, ?, ?, ?, ?)}");
			sentencia.setString(1,producto.getNombre());
			sentencia.setString(2,producto.getDescripcion());
			sentencia.setString(3,producto.getMarca());
			sentencia.setString(4,producto.getPresentacion());
			sentencia.setInt(5, producto.getStock());
			sentencia.setDouble(6,producto.getPrecio());
			rs = sentencia.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("insertarProducto ERRR" + e);
			e.printStackTrace();
		}
	}

	@Override
	public Producto obtenerProductoPorId(int idProducto) {
		Producto producto = null;
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_listarProductoById(?)}");
			sentencia.setInt(1,idProducto);
			rs = sentencia.executeQuery();

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getInt("idProducto"));
		        producto.setNombre(rs.getString("nombre"));
		        producto.setDescripcion(rs.getString("descripcion"));
		        producto.setMarca(rs.getString("marca"));
		        producto.setPresentacion(rs.getString("presentacion"));
		        producto.setStock(rs.getInt("stock"));
		        producto.setPrecio(rs.getDouble("precio"));
			}
			
		} catch (SQLException e) {
			System.out.println("ERRRROR");
			e.printStackTrace();
		}

		return producto;
	}

	@Override
	public void actualizarProducto(Producto producto) {
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_modificarProducto(?, ?, ?, ?, ?, ?, ?)}");
			sentencia.setString(1,producto.getNombre());
			sentencia.setString(2,producto.getDescripcion());
			sentencia.setString(3,producto.getMarca());
			sentencia.setString(4,producto.getPresentacion());
			sentencia.setInt(5, producto.getStock());
			sentencia.setDouble(6,producto.getPrecio());
			sentencia.setInt(7,producto.getIdProducto());
			rs = sentencia.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Modificar Cliente ERRR" + e);
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarProducto(int idProducto) {
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_eliminarProducto(?)}");
			sentencia.setInt(1,idProducto);
			rs = sentencia.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("ERRRROR");
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Producto> obtenerTodosLosProductos(String filtro) {
		ArrayList<Producto> listProducto = new ArrayList<Producto>();
		ResultSet rs = null;

		CallableStatement sentencia;
		
		
		try {
			sentencia = conex.prepareCall("{call usp_listarProducto(?)}");
			sentencia.setString(1,filtro);
			rs = sentencia.executeQuery();

			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProducto"));
		        producto.setNombre(rs.getString("nombre"));
		        producto.setDescripcion(rs.getString("descripcion"));
		        producto.setMarca(rs.getString("marca"));
		        producto.setPresentacion(rs.getString("presentacion"));
		        producto.setStock(rs.getInt("stock"));
		        producto.setPrecio(rs.getDouble("precio"));
		        listProducto.add(producto);
			}
			
		} catch (SQLException e) {
			System.out.println("ERRRROR");
			e.printStackTrace();
		}

		return listProducto;
	}

	@Override
	public int actualizarStock(int idProducto, int newStock) {
        int filasAlteradas=0;
        String sql = "update producto set stock = ?\r\n"
        		+ "where idProducto = ? ;";
        try (
            PreparedStatement stmt = conex.prepareStatement(sql)) {
        	stmt.setInt(1, newStock);
            stmt.setInt(2, idProducto);

            filasAlteradas= stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAlteradas;
	}
	

}
