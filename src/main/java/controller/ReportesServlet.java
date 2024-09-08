package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Reporte;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dao.ReporteImpl;

/**
 * Servlet implementation class ReportesServlet
 */
public class ReportesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReporteImpl reporteImpl = new ReporteImpl();
        Reporte reporteProductos = reporteImpl.obtenerReporteProducto();
        Reporte reporteEmpleado = reporteImpl.obtenerReporteEmpleados();
        
        String productosTags = convertArrayStringToJson(reporteProductos.getTags());
        String productosValues = convertArrayIntToJson(reporteProductos.getValores());
        
        String empleadosTags = convertArrayStringToJson(reporteEmpleado.getTags());
        String empleadosValues = convertArrayIntToJson(reporteEmpleado.getValores());
        
        String dataReporte = "{\"reporteProductos\":{\"tags\":"+productosTags+",\"valores\": "+productosValues+"},"
        		+ "\"reporteEmpleados\":{\"tags\":"+empleadosTags+",\"valores\": "+empleadosValues+"}}";
        System.out.println("DATOS :"+ dataReporte);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(dataReporte);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static String convertArrayIntToJson(ArrayList<Double> array) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        for (int i = 0; i < array.size(); i++) {
            jsonBuilder.append(array.get(i));
            if (i < array.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
	public static String convertArrayStringToJson(ArrayList<String> array) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        for (int i = 0; i < array.size(); i++) {
            jsonBuilder.append("\""+array.get(i)+"\"");
            if (i < array.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

}
