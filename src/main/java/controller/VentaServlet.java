package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DetalleVenta;
import model.Producto;
import model.Venta;
import model.Empleado;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.VentaImpl;
import dao.ProductoImpl;
/**
 * Servlet implementation class VentaServlet
 */
public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VentaImpl daoVenta = new VentaImpl();
	ProductoImpl daoProducto = new ProductoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = "Venta.jsp";
		String mensaje = "";
		ArrayList<DetalleVenta> detalle = (ArrayList<DetalleVenta>)request.getSession().getAttribute("DetalleVenta");
		if(detalle ==null) {
			detalle = new ArrayList<DetalleVenta>();
		}
		if(!action.equals(null)) {
			if(action.equals("ADDPRODUCTO")) {
				int idProducto = Integer.parseInt(request.getParameter("idProducto"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				Producto producto = daoProducto.obtenerProductoPorId(idProducto);
				System.out.println("PRODUCTO ===>" +producto);
				boolean encontrado = false;
				for (int i = 0; i < detalle.size(); i++) {
					if(detalle.get(i).getProducto().getIdProducto()==idProducto) {
						encontrado = true;
						mensaje = "No se puede agregar el producto por que ya está en la lista.";
						request.setAttribute("mensaje", mensaje);
					}
				}
				if(!encontrado) {
					if(cantidad > producto.getStock()) {
						mensaje = "No se puede agregar el producto por que no se cuenta con el stock suficiente.";
						request.setAttribute("mensaje", mensaje);
					}else {
						DetalleVenta dv = new DetalleVenta();
						dv.setCantidad(cantidad);
						dv.setProducto(producto);
						detalle.add(dv);
						request.getSession().setAttribute("DetalleVenta", detalle);
					}
					
				}
				
			}
			if(action.equals("REMOVEPRODUCTO")) {
				int idProducto = Integer.parseInt(request.getParameter("idProducto"));
				int postProductoEncontrada = -1;
				for (int i = 0; i < detalle.size(); i++) {
					if(detalle.get(i).getProducto().getIdProducto()==idProducto) {
						postProductoEncontrada=i;
						break;
					}
				}
				if(postProductoEncontrada!=-1) {
					detalle.remove(postProductoEncontrada);
				}
			}
			if(action.equals("GRABARVENTA")) {
				if(detalle.size()!=0) {
					int cliente = Integer.parseInt(request.getParameter("cliente"));
					Empleado empleadoSession = (Empleado)request.getSession().getAttribute("user");
					String fecha = request.getParameter("fecha");
					String formatoFecha = "yyyy-MM-dd"; 
					SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
					Venta venta = new Venta();
					try {
						venta.setFecha(sdf.parse(fecha));
						venta.setIdCliente(cliente);
						venta.setIdVendedor(empleadoSession.getIdEmpleado());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					int cambios = daoVenta.crearVenta(venta);
					if(cambios>0) {
						int idVenta = daoVenta.traerUltimaVenta();
						for (int i = 0; i < detalle.size(); i++) {
							DetalleVenta vd = detalle.get(i);
							int idProducto = vd.getProducto().getIdProducto();
							int stock = vd.getProducto().getStock();
							int cantidad = vd.getCantidad();
							daoProducto.actualizarStock(idProducto, stock-cantidad);
							vd.setIdVenta(idVenta);
							daoVenta.crearDetalleVenta(vd);
						}
						request.getSession().setAttribute("DetalleVenta", new ArrayList<DetalleVenta>());
						mensaje = "Venta Exitosa.";
						request.setAttribute("mensaje", mensaje);	
					}else {
						mensaje = "No se pudo realizar la venta.";
						request.setAttribute("mensaje", mensaje);				
					}
					
				}
			}
		}
		request.getRequestDispatcher(url).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doGet(request, response);
	}

}
