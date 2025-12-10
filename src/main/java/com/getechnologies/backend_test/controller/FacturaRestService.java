package com.getechnologies.backend_test.controller;

import com.getechnologies.backend_test.model.Factura;
import com.getechnologies.backend_test.service.Ventas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class FacturaRestService {

    @Autowired
    private Ventas ventas;

    // POST: Crear una factura para una persona de acuerdo a la identificacion
    // Ejemplo URL: localhost:8080/api/ventas/ID001/factura
    @PostMapping("/{identificacion}/factura")
    public ResponseEntity<Factura> createFactura(@PathVariable String identificacion, @RequestBody Factura factura) {
        return new ResponseEntity<>(ventas.storeFactura(identificacion, factura), HttpStatus.CREATED);
    }

    // GET: Ver todas las facturas de una persona
    // Ejemplo URL: localhost:8080/api/ventas/ID001/factura
    @GetMapping("/{identificacion}/factura")
    public ResponseEntity<List<Factura>> getFacturasByPersona(@PathVariable String identificacion) {
        return ResponseEntity.ok(ventas.findFacturasByPersona(identificacion));
    }
}