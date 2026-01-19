package com.pruebatecnica.banco.controller;

import com.pruebatecnica.banco.entity.Cuenta;
import com.pruebatecnica.banco.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes/{clienteId}/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // =============================
    // CREAR CUENTA
    // =============================
    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(
            @PathVariable Long clienteId,
            @RequestBody Cuenta cuenta
    ) {
        Cuenta creada = cuentaService.crearCuenta(clienteId, cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    // =============================
    // LISTAR CUENTAS
    // =============================
    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas(
            @PathVariable Long clienteId
    ) {
        return ResponseEntity.ok(
                cuentaService.listarCuentasPorCliente(clienteId)
        );
    }

    // =============================
    // OBTENER CUENTA
    // =============================
    @GetMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> obtenerCuenta(
            @PathVariable Long clienteId,
            @PathVariable Long cuentaId
    ) {
        return ResponseEntity.ok(
                cuentaService.obtenerCuenta(clienteId, cuentaId)
        );
    }

    // =============================
    // CANCELAR CUENTA
    // =============================
    @PutMapping("/{cuentaId}/cancelar")
    public ResponseEntity<Map<String, String>> cancelarCuenta(
            @PathVariable Long clienteId,
            @PathVariable Long cuentaId
    ) {
        cuentaService.cancelarCuenta(clienteId, cuentaId);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cuenta cancelada correctamente");

        return ResponseEntity.ok(response);
    }
}
