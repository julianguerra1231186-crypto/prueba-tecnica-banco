package com.pruebatecnica.banco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.banco.dto.TransferenciaRequest;
import com.pruebatecnica.banco.service.TransaccionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransaccionController.class)
class TransaccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransaccionService transaccionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void transferencia_deberiaRetornar200() throws Exception {

        TransferenciaRequest request =
                new TransferenciaRequest(1L, 2L, 100000.0);

        when(transaccionService.transferir(any(TransferenciaRequest.class)))
                .thenReturn("Transferencia exitosa");

        mockMvc.perform(post("/api/transacciones/transferir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
