package com.getechnologies.backend_test.repository;

import com.getechnologies.backend_test.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}