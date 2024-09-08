package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ProductoImpl;
import model.Producto;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class ProductoMantenedorDB
 */
public class ProductoMantenedorDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoMantenedorDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ProductoImpl productoService = new ProductoImpl();
		
		if(action == null) {
			action = "FIND";
		}
		if (action.equals("LISTARTODOS")) {
			ArrayList<Producto> listaProductos = productoService.obtenerTodosLosProductos("");
			
			request.setAttribute("listaProductos", listaProductos);
			request.setAttribute("filtro", "");

			request.getRequestDispatcher("ProductoListar.jsp").forward(request, response);
		}
		if (action.equals("FIND")) {
			String filtro = request.getParameter("filtro");
			if(filtro == null) {
				filtro = "";
			}
			ArrayList<Producto> listaProductos = productoService.obtenerTodosLosProductos(filtro);
			
			request.setAttribute("listaProductos", listaProductos);
			request.setAttribute("filtro", filtro);

			request.getRequestDispatcher("ProductoListar.jsp").forward(request, response);
		}
		
		if (action.equals("NEW")) {
			Producto prod = new Producto();
			prod.setNombre("");
			prod.setDescripcion("");
			prod.setMarca("");
			prod.setPresentacion("");
			prod.setStock(0);
			prod.setPrecio(0);
			request.setAttribute("titulo", "Crear Nuevo Producto");
			request.setAttribute("producto", prod);
			request.setAttribute("action", "NEW");
			request.getRequestDispatcher("Producto.jsp").forward(request, response);
		}
		if(action.equals("EDIT")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Producto cli = productoService.obtenerProductoPorId(id);
			request.setAttribute("titulo", "Modificar Producto");
			request.setAttribute("producto", cli);
			request.setAttribute("action", "EDIT");
			request.getRequestDispatcher("Producto.jsp").forward(request, response);
		}
		
		if(action.equals("REMOVE")) {
			int id = Integer.parseInt(request.getParameter("id"));
			productoService.eliminarProducto(id);
			response.sendRedirect("ProductoListar.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ProductoImpl productoService = new ProductoImpl();
		Producto prod = new Producto();
		String nombre = request.getParameter("nombre");

		String descripcion = request.getParameter("descripcion");

		String marca = request.getParameter("marca");

		String presentacion = request.getParameter("presentacion");

		Integer stock = Integer.parseInt(request.getParameter("stock"));

		Double precio = Double.parseDouble(request.getParameter("precio"));

		prod.setNombre(nombre);
		prod.setDescripcion(descripcion);
		prod.setMarca(marca);
		prod.setPresentacion(presentacion);
		prod.setStock(stock);
		prod.setPrecio(precio);
		if(action.equals("NEW")) {
			productoService.insertarProducto(prod);
			response.sendRedirect("ProductoListar.jsp");
		}
		if(action.equals("EDIT")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			prod.setIdProducto(id);
			productoService.actualizarProducto(prod);
			response.sendRedirect("ProductoListar.jsp");
		}
	}

}
