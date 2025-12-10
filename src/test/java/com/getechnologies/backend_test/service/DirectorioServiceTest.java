package com.getechnologies.backend_test.service;

import com.getechnologies.backend_test.exception.ResourceNotFoundException;
import com.getechnologies.backend_test.model.Persona;
import com.getechnologies.backend_test.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito
public class DirectorioServiceTest {

    @Mock // Simulación base de datos
    private PersonaRepository personaRepository;

    @InjectMocks // Inyectar el mock en nuestro servicio real
    private Directorio directorio;

    // TEST 1: Verificar que se guarde una persona correctamente.
    @Test
    void guardarPersona_DeberiaRetornarPersonaGuardada() {
        // Preparar datos (Arrange)
        Persona personaInput = new Persona();
        personaInput.setNombre("Laura");
        personaInput.setIdentificacion("TEST01");

        // Guardar
        when(personaRepository.save(any(Persona.class))).thenReturn(personaInput);

        // Ejecutar
        Persona resultado = directorio.storePersona(personaInput);

        // Verificar
        assertNotNull(resultado);
        assertEquals("Laura", resultado.getNombre());
        verify(personaRepository, times(1)).save(personaInput);
    }

    // TEST 2: Verificar que busca correctamente por ID
    @Test
    void buscarPorIdentificacion_DeberiaRetornarPersona_CuandoExiste() {
        Persona personaExistente = new Persona();
        personaExistente.setIdentificacion("TEST01");

        // Simular que la BD encuentra algo
        when(personaRepository.findByIdentificacion("TEST01")).thenReturn(Optional.of(personaExistente));

        Persona resultado = directorio.findPersonaByIdentificacion("TEST01");

        assertEquals("TEST01", resultado.getIdentificacion());
    }

    // TEST 3: Verificar que lanza error si la persona no existe
    @Test
    void buscarPorIdentificacion_DeberiaLanzarError_CuandoNoExiste() {

        when(personaRepository.findByIdentificacion("FANTASMA")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            directorio.findPersonaByIdentificacion("FANTASMA");
        });
    }
}