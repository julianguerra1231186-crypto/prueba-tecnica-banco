package com.pruebatecnica.banco.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReporteTransaccionesResponse {

    private String tipo;
    private BigDecimal monto;
    private LocalDateTime fecha;

    public ReporteTransaccionesResponse(
            String tipo,
            BigDecimal monto,
            LocalDateTime fecha
    ) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
