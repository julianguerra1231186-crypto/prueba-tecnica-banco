package com.pruebatecnica.banco.service;

import com.pruebatecnica.banco.entity.Cliente;
import com.pruebatecnica.banco.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente con ID " + id + " no existe")
                );
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente existente = obtenerCliente(id);

        existente.setNombres(cliente.getNombres());
        existente.setApellidos(cliente.getApellidos());
        existente.setCorreoElectronico(cliente.getCorreoElectronico());
        existente.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        existente.setTipoIdentificacion(cliente.getTipoIdentificacion());
        existente.setFechaNacimiento(cliente.getFechaNacimiento());

        return clienteRepository.save(existente);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente existente = obtenerCliente(id);
        clienteRepository.delete(existente);
    }
}
