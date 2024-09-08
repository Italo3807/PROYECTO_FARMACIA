package dao;

import model.Empleado;

public interface EmpleadoInterface {
	public Empleado login(String correo);

    int crearEmpleado(Empleado empleado);
}
