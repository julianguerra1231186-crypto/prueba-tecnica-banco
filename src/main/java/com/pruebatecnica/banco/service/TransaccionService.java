package com.pruebatecnica.banco.service;

import com.pruebatecnica.banco.entity.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransaccionService {

    // Deposito / Retiro
    Transaccion realizarTransaccion(Long cuentaId, String tipo, BigDecimal monto);

    // Transferencia
    void transferir(Long cuentaOrigenId, Long cuentaDestinoId, BigDecimal monto);

    // Reporte / Estado de cuenta
    List<Transaccion> reporte(
            Long cuentaId,
            LocalDateTime desde,
            LocalDateTime hasta
    );
}
