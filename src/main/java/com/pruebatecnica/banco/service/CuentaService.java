package com.pruebatecnica.banco.service;

import com.pruebatecnica.banco.entity.Cuenta;

import java.util.List;

public interface CuentaService {

    Cuenta crearCuenta(Long clienteId, Cuenta cuenta);

    List<Cuenta> listarCuentasPorCliente(Long clienteId);

    Cuenta obtenerCuenta(Long clienteId, Long cuentaId);

    void cancelarCuenta(Long clienteId, Long cuentaId);
}
