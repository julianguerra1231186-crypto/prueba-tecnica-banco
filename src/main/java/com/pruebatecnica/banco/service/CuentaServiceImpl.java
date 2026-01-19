package com.pruebatecnica.banco.service;

import com.pruebatecnica.banco.entity.Cliente;
import com.pruebatecnica.banco.entity.Cuenta;
import com.pruebatecnica.banco.entity.EstadoCuenta;
import com.pruebatecnica.banco.repository.ClienteRepository;
import com.pruebatecnica.banco.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaServiceImpl(
            CuentaRepository cuentaRepository,
            ClienteRepository clienteRepository
    ) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    // ===============================
    // CREAR CUENTA
    // ===============================
    @Override
    public Cuenta crearCuenta(Long clienteId, Cuenta cuenta) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cuenta.setCliente(cliente);
        cuenta.setSaldo(BigDecimal.ZERO);
        cuenta.setEstado(EstadoCuenta.ACTIVA);
        cuenta.setFechaCreacion(LocalDateTime.now());
        cuenta.setNumeroCuenta(generarNumeroCuenta());

        return cuentaRepository.save(cuenta);
    }

    // ===============================
    // LISTAR CUENTAS POR CLIENTE
    // ===============================
    @Override
    public List<Cuenta> listarCuentasPorCliente(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }

    // ===============================
    // OBTENER CUENTA
    // ===============================
    @Override
    public Cuenta obtenerCuenta(Long clienteId, Long cuentaId) {
        return cuentaRepository.findById(cuentaId)
                .filter(c -> c.getCliente().getId().equals(clienteId))
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    // ===============================
    // CANCELAR CUENTA
    // ===============================
    @Override
    public void cancelarCuenta(Long clienteId, Long cuentaId) {

        Cuenta cuenta = obtenerCuenta(clienteId, cuentaId);

        if (cuenta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
            throw new RuntimeException("No se puede cancelar una cuenta con saldo");
        }

        cuenta.setEstado(EstadoCuenta.CANCELADA);
        cuentaRepository.save(cuenta);
    }

    // ===============================
    // GENERAR NÚMERO DE CUENTA
    // 53 / 33 + 10 dígitos
    // ===============================
    private String generarNumeroCuenta() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("53");

        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
