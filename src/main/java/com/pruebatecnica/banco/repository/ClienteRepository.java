package com.pruebatecnica.banco.repository;

import com.pruebatecnica.banco.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio para la gestión de la entidad Cliente.
 * Proporciona acceso a la persistencia de datos.
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
