package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.EmpleadoImpl;
import model.Empleado;
import utilidades.Metodos_contraseña;
import model.DetalleVenta;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true);
		if (request.getSession() != null) {
			// Invalidar la sesión para eliminarla
			request.getSession().invalidate();
			System.out.println("Sesión eliminada correctamente.");
		} else {
			System.out.println("No hay sesión activa.");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String correo = request.getParameter("correo");
		String password = request.getParameter("pass");

		EmpleadoImpl empService = new EmpleadoImpl();

		Empleado empleado = empService.login(correo);

		if (empleado != null) {
			
			System.out.println(empleado.getNombre());
			String hash=empleado.getContrasenia();
			String salt=empleado.getSalt();
			try {
				boolean contraseña_verificada=Metodos_contraseña.verificarContraseña(password, hash, salt);
				if(correo.equals(empleado.getCorreo())&&contraseña_verificada) {
					request.getSession(true);
					ArrayList<DetalleVenta> detalleVenta = new ArrayList<DetalleVenta>();
					request.getSession().setAttribute("user", empleado);

					request.getSession().setAttribute("DetalleVenta", detalleVenta);
					response.sendRedirect("ClienteListar.jsp");
				}else {
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}	
		}else {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
