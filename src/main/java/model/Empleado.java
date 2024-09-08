package model;
import java.util.Date;

public class Empleado {
	private int idEmpleado;
    private String dniEmpleado;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private Date fechaNacimiento;
    private String correo;
    private String celular;
    private String contrasenia;
    private String salt;
    
    
	public Empleado() {
		super();
	}
	public Empleado(int idEmpleado, String dniEmpleado, String nombre, String apePaterno, String apeMaterno,
			Date fechaNacimiento, String correo, String celular, String contrasenia) {
		super();
		this.idEmpleado = idEmpleado;
		this.dniEmpleado = dniEmpleado;
		this.nombre = nombre;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.celular = celular;
		this.contrasenia = contrasenia;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDniEmpleado() {
		return dniEmpleado;
	}
	public void setDniEmpleado(String dniEmpleado) {
		this.dniEmpleado = dniEmpleado;
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
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", dniEmpleado=" + dniEmpleado + ", nombre=" + nombre
				+ ", apePaterno=" + apePaterno + ", apeMaterno=" + apeMaterno + ", fechaNacimiento=" + fechaNacimiento
				+ ", correo=" + correo + ", celular=" + celular + ", contrasenia=" + contrasenia + ", salt=" + salt
				+ "]";
	}
    
    
}
