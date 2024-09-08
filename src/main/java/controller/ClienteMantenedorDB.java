package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ClienteImpl;
import model.Cliente;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Servlet implementation class ClienteMantenedorDB
 */
public class ClienteMantenedorDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteMantenedorDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ClienteImpl clienteService = new ClienteImpl();
		if(action == null) {
			action = "FIND";
		}
		if (action.equals("LISTARTODOS")) {
			ArrayList<Cliente> listaClientes = clienteService.obtenerTodosLosClientes("");
			
			request.setAttribute("listaClientes", listaClientes);
			request.setAttribute("filtro", "");

			request.getRequestDispatcher("ClienteListar.jsp").forward(request, response);
		}
		if (action.equals("FIND")) {
			String filtro = request.getParameter("filtro");
			if(filtro == null) {
				filtro = "";
			}
			ArrayList<Cliente> listaClientes = clienteService.obtenerTodosLosClientes(filtro);
			
			request.setAttribute("listaClientes", listaClientes);
			request.setAttribute("filtro", filtro);

			request.getRequestDispatcher("ClienteListar.jsp").forward(request, response);
		}
		
		if (action.equals("NEW")) {
			Cliente cli = new Cliente();
			cli.setDniCliente("");
			cli.setNombre("");
			cli.setApePaterno("");
			cli.setApeMaterno("");
			cli.setFechaNacimiento(new Date());
			cli.setCorreo("");
			cli.setCelular("");
			request.setAttribute("titulo", "Crear Nuevo Cliente");
			request.setAttribute("cliente", cli);
			request.setAttribute("action", "NEW");
			request.getRequestDispatcher("Cliente.jsp").forward(request, response);
		}
		if(action.equals("EDIT")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Cliente cli = clienteService.obtenerClientePorId(id);
			request.setAttribute("titulo", "Modificar Cliente");
			request.setAttribute("cliente", cli);
			request.setAttribute("action", "EDIT");
			request.getRequestDispatcher("Cliente.jsp").forward(request, response);
		}
		
		if(action.equals("REMOVE")) {
			int id = Integer.parseInt(request.getParameter("id"));
			clienteService.eliminarCliente(id);
			response.sendRedirect("ClienteListar.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ClienteImpl clienteService = new ClienteImpl();
		Cliente cliente = new Cliente();
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");

		String apePaterno = request.getParameter("apePaterno");

		String apeMaterno = request.getParameter("apeMaterno");

		String fechaNacimiento = request.getParameter("fechaNacimiento");

		String email = request.getParameter("email");

		String celular = request.getParameter("celular");
		
		String formatoFecha = "yyyy-MM-dd"; 

		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		try {
			cliente.setDniCliente(dni);
			cliente.setNombre(nombre);
			cliente.setApePaterno(apePaterno);
			cliente.setApeMaterno(apeMaterno);
			cliente.setFechaNacimiento(sdf.parse(fechaNacimiento));
			cliente.setCorreo(email);
			cliente.setCelular(celular);
			
		} catch (ParseException e) {
			System.out.println("ERROR"+ e);
		}
		if(action.equals("NEW")) {
			clienteService.insertarCliente(cliente);
			response.sendRedirect("ClienteListar.jsp");
		}
		if(action.equals("EDIT")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			cliente.setIdCliente(id);
			clienteService.actualizarCliente(cliente);
			response.sendRedirect("ClienteListar.jsp");
		}
	}

}
