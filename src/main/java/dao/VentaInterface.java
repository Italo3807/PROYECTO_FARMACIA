package dao;

import model.DetalleVenta;
import model.Venta;

public interface VentaInterface {
	int crearVenta(Venta venta);
	int crearDetalleVenta(DetalleVenta detalleVenta);
	int traerUltimaVenta();
}
