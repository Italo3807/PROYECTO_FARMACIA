package dao;

import java.util.ArrayList;

import model.Cliente;

public interface ClienteInterface {
    void insertarCliente(Cliente cliente);

    Cliente obtenerClientePorId(int idCliente);

    ArrayList<Cliente> obtenerTodosLosClientes(String filtro);

    void actualizarCliente(Cliente cliente);

    void eliminarCliente(int idCliente);
}
