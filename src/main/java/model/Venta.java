package model;
import java.util.ArrayList;
import java.util.Date;
public class Venta {
	private int idCliente;
	private int idVendedor;
	private Date fecha;
	private ArrayList<DetalleVenta> detalleVenta;
	
	public Venta() {
		// TODO Auto-generated constructor stub
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(ArrayList<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	
	
}
