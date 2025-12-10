package com.getechnologies.backend_test.service;

import com.getechnologies.backend_test.model.Persona;
import com.getechnologies.backend_test.repository.PersonaRepository;
import com.getechnologies.backend_test.exception.ResourceNotFoundException;
import org.slf4j.Logger; // Librería SLF4J recomendada [cite: 26]
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class Directorio {
    private static final Logger logger = LoggerFactory.getLogger(Directorio.class);

    @Autowired
    private PersonaRepository personaRepository;

    public Persona storePersona(Persona persona) {
        logger.info("Guardando persona: " + persona.getNombre());
        return personaRepository.save(persona);
    }

    public List<Persona> findPersonas() {
        return personaRepository.findAll();
    }

    public Persona findPersonaByIdentificacion(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada: " + identificacion));
    }

    @Transactional
    public void deletePersonaByIdentificacion(String identificacion) {
        Persona p = findPersonaByIdentificacion(identificacion);
        logger.info("Eliminando persona e información relacionada: " + identificacion);
        personaRepository.delete(p); // Al borrar esto, JPA borra las facturas por el CascadeType.ALL
    }
}