package com.pruebatecnica.banco.dto;

import java.math.BigDecimal;
/**
 * DTO de respuesta que representa el estado de una cuenta.
 * Contiene la información necesaria para exponer el saldo
 * y movimientos asociados al cliente.
 */

public class EstadoCuentaResponse {

    private Long cuentaId;
    private String numeroCuenta;
    private String tipoCuenta;
    private String estado;
    private BigDecimal saldo;

    public EstadoCuentaResponse(
            Long cuentaId,
            String numeroCuenta,
            String tipoCuenta,
            String estado,
            BigDecimal saldo
    ) {
        this.cuentaId = cuentaId;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.estado = estado;
        this.saldo = saldo;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public String getEstado() {
        return estado;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
