package com.itsqmet.ejemplotaller2.repository;

import com.itsqmet.ejemplotaller2.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<cliente, Long> {
}