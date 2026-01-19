package com.pruebatecnica.banco.service;

import com.pruebatecnica.banco.entity.Cuenta;
import com.pruebatecnica.banco.entity.TipoTransaccion;
import com.pruebatecnica.banco.entity.Transaccion;
import com.pruebatecnica.banco.exception.BadRequestException;
import com.pruebatecnica.banco.repository.CuentaRepository;
import com.pruebatecnica.banco.repository.TransaccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransaccionServiceImpl implements TransaccionService {

    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;

    public TransaccionServiceImpl(
            CuentaRepository cuentaRepository,
            TransaccionRepository transaccionRepository
    ) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public Transaccion realizarTransaccion(Long cuentaId, String tipo, BigDecimal monto) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new BadRequestException("Cuenta no encontrada"));

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("El monto debe ser mayor a 0");
        }

        TipoTransaccion tipoTransaccion = TipoTransaccion.valueOf(tipo);

        if (tipoTransaccion == TipoTransaccion.RETIRO &&
                cuenta.getSaldo().compareTo(monto) < 0) {
            throw new BadRequestException("Saldo insuficiente para retiro");
        }

        if (tipoTransaccion == TipoTransaccion.DEPOSITO) {
            cuenta.setSaldo(cuenta.getSaldo().add(monto));
        } else {
            cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        }

        cuentaRepository.save(cuenta);

        Transaccion transaccion = new Transaccion();
        transaccion.setCuenta(cuenta);
        transaccion.setTipo(tipoTransaccion);
        transaccion.setMonto(monto);
        transaccion.setFecha(LocalDateTime.now());

        return transaccionRepository.save(transaccion);
    }

    @Override
    public void transferir(Long cuentaOrigenId, Long cuentaDestinoId, BigDecimal monto) {

        if (cuentaOrigenId.equals(cuentaDestinoId)) {
            throw new BadRequestException("No se puede transferir a la misma cuenta");
        }

        Cuenta origen = cuentaRepository.findById(cuentaOrigenId)
                .orElseThrow(() -> new BadRequestException("Cuenta origen no encontrada"));

        Cuenta destino = cuentaRepository.findById(cuentaDestinoId)
                .orElseThrow(() -> new BadRequestException("Cuenta destino no encontrada"));

        if (origen.getSaldo().compareTo(monto) < 0) {
            throw new BadRequestException("Saldo insuficiente para transferencia");
        }

        origen.setSaldo(origen.getSaldo().subtract(monto));
        destino.setSaldo(destino.getSaldo().add(monto));

        cuentaRepository.save(origen);
        cuentaRepository.save(destino);

        Transaccion salida = new Transaccion();
        salida.setCuenta(origen);
        salida.setTipo(TipoTransaccion.RETIRO);
        salida.setMonto(monto);
        salida.setFecha(LocalDateTime.now());

        Transaccion entrada = new Transaccion();
        entrada.setCuenta(destino);
        entrada.setTipo(TipoTransaccion.DEPOSITO);
        entrada.setMonto(monto);
        entrada.setFecha(LocalDateTime.now());

        transaccionRepository.save(salida);
        transaccionRepository.save(entrada);
    }

    @Override
    public List<Transaccion> reporte(
            Long cuentaId,
            LocalDateTime desde,
            LocalDateTime hasta
    ) {
        return transaccionRepository
                .findByCuentaIdAndFechaBetween(cuentaId, desde, hasta);
    }
}
