package com.pruebatecnica.banco.repository;

import com.pruebatecnica.banco.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
