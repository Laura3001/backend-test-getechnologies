package com.getechnologies.backend_test.controller;

import com.getechnologies.backend_test.model.Persona;
import com.getechnologies.backend_test.service.Directorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/directorio")
public class DirectorioRestService {

    @Autowired
    private Directorio directorio;

    @PostMapping
    public ResponseEntity<Persona> crear(@Valid @RequestBody Persona persona) {
        return new ResponseEntity<>(directorio.storePersona(persona), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Persona>> listar() {
        return ResponseEntity.ok(directorio.findPersonas());
    }

    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Void> borrar(@PathVariable String identificacion) {
        directorio.deletePersonaByIdentificacion(identificacion);
        return ResponseEntity.noContent().build();
    }
}
