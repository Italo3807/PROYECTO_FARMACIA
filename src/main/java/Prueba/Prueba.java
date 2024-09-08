package Prueba;
import dao.EmpleadoImpl;
import model.Empleado;
public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpleadoImpl empl= new EmpleadoImpl();
		Empleado emp = new Empleado();
		emp.setNombre("ROXANA");
		emp.setApeMaterno("BACA");
		emp.setApePaterno("VILCHEZ");
		emp.setCelular("958977830");
		emp.setContrasenia("admin");
		emp.setCorreo("roxy14@hotmail.com");
		emp.setDniEmpleado("72948633");
		
		empl.crearEmpleado(emp);
		
		
		Empleado emp1 = new Empleado();
		emp1.setNombre("LUISA");
		emp1.setApeMaterno("BACA");
		emp1.setApePaterno("VILCHEZ");
		emp1.setCelular("958977833");
		emp1.setContrasenia("admin");
		emp1.setCorreo("luisa14@hotmail.com");
		emp1.setDniEmpleado("72948632");
		
		empl.crearEmpleado(emp1);
	}

}
