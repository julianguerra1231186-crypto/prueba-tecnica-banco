package com.pruebatecnica.banco.service;

import com.pruebatecnica.banco.entity.Cliente;
import java.util.List;

public interface ClienteService {

    Cliente crearCliente(Cliente cliente);

    Cliente obtenerCliente(Long id);

    Cliente actualizarCliente(Long id, Cliente cliente);

    void eliminarCliente(Long id);

    List<Cliente> listarClientes();
}
