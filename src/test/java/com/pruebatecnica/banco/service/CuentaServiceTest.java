package com.pruebatecnica.banco.service;

import com.pruebatecnica.banco.entity.Cliente;
import com.pruebatecnica.banco.entity.Cuenta;
import com.pruebatecnica.banco.repository.ClienteRepository;
import com.pruebatecnica.banco.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @Test
    void crearCuenta_debeCrearCuentaConSaldoCero() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Cuenta cuenta = new Cuenta();

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(cuentaRepository.save(any(Cuenta.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Cuenta resultado = cuentaService.crearCuenta(1L, cuenta);

        assertNotNull(resultado);
        assertEquals(0, resultado.getSaldo().intValue());
        assertNotNull(resultado.getNumeroCuenta());

        verify(clienteRepository).findById(1L);
        verify(cuentaRepository).save(any(Cuenta.class));
    }
}
