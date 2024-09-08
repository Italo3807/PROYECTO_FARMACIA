package model;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private String marca;
    private String presentacion;
    private int stock;
    private double precio;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con todos los campos
    public Producto(int idProducto, String nombre, String descripcion, String marca,
                    String presentacion, int stock, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.presentacion = presentacion;
        this.stock = stock;
        this.precio = precio;
    }

    // Setters y Getters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", marca="
				+ marca + ", presentacion=" + presentacion + ", stock=" + stock + ", precio=" + precio + "]";
	}
    
    
}
