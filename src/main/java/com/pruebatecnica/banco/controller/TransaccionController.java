package com.pruebatecnica.banco.controller;

import com.pruebatecnica.banco.dto.TransferenciaRequest;
import com.pruebatecnica.banco.entity.Transaccion;
import com.pruebatecnica.banco.service.TransaccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    // =============================
    // DEPOSITO / RETIRO
    // =============================
    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<Transaccion> realizarTransaccion(
            @PathVariable Long cuentaId,
            @RequestBody Map<String, String> body
    ) {
        Transaccion t = transaccionService.realizarTransaccion(
                cuentaId,
                body.get("tipo"),
                new BigDecimal(body.get("monto"))
        );
        return ResponseEntity.ok(t);
    }

    // =============================
    // TRANSFERENCIA
    // =============================
    @PostMapping("/transferir")
    public ResponseEntity<Map<String, String>> transferir(
            @RequestBody TransferenciaRequest request
    ) {
        transaccionService.transferir(
                request.getCuentaOrigenId(),
                request.getCuentaDestinoId(),
                request.getMonto()
        );

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Transferencia realizada correctamente");

        return ResponseEntity.ok(response);
    }

    // =============================
    // REPORTE / ESTADO DE CUENTA
    // =============================
    @GetMapping("/cuenta/{cuentaId}/reporte")
    public ResponseEntity<List<Transaccion>> reporte(
            @PathVariable Long cuentaId,
            @RequestParam String desde,
            @RequestParam String hasta
    ) {
        return ResponseEntity.ok(
                transaccionService.reporte(
                        cuentaId,
                        LocalDateTime.parse(desde),
                        LocalDateTime.parse(hasta)
                )
        );
    }
}
