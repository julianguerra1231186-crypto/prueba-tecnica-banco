package com.pruebatecnica.banco.repository;

import com.pruebatecnica.banco.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByCuentaId(Long cuentaId);

    List<Transaccion> findByCuentaIdAndFechaBetween(
            Long cuentaId,
            LocalDateTime desde,
            LocalDateTime hasta
    );
}
