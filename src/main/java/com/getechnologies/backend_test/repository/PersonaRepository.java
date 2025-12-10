package com.getechnologies.backend_test.repository;

import com.getechnologies.backend_test.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // Método personalizado para buscar por identificación
    Optional<Persona> findByIdentificacion(String identificacion);
}