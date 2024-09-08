package dao;

import java.util.ArrayList;

import model.Producto;

public interface ProductoInterface {
	void insertarProducto(Producto producto);
    Producto obtenerProductoPorId(int idProducto);
    void actualizarProducto(Producto producto);
    void eliminarProducto(int idProducto);
    ArrayList<Producto> obtenerTodosLosProductos(String filtro);
    int actualizarStock(int idProducto, int newStock);
}
