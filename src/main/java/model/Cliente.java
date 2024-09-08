package model;
import java.util.Date;
public class Cliente {
    // Atributos
    private int idCliente;
    private String dniCliente;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private Date fechaNacimiento;
    private String correo;
    private String celular;

    public Cliente(){
    	super();
    }
    // Constructor
    public Cliente(int idCliente, String dniCliente, String nombre, String apePaterno, String apeMaterno,
                   Date fechaNacimiento, String correo, String celular) {
        this.idCliente = idCliente;
        this.dniCliente = dniCliente;
        this.nombre = nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.celular = celular;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    // Método toString para representar el objeto en forma de cadena de texto
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", dniCliente='" + dniCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apePaterno='" + apePaterno + '\'' +
                ", apeMaterno='" + apeMaterno + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correo='" + correo + '\'' +
                ", celular='" + celular + '\'' +
                '}';
    }
}
