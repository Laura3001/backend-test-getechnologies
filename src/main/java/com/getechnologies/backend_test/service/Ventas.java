package com.getechnologies.backend_test.service;

import com.getechnologies.backend_test.model.Factura;
import com.getechnologies.backend_test.model.Persona;
import com.getechnologies.backend_test.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Ventas {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private Directorio directorio; // Reusamos el servicio de Directorio para buscar personas

    public Factura storeFactura(String identificacionPersona, Factura factura) {
        // 1. Buscamos a la persona (si no existe, el servicio Directorio lanzará el error 404)
        Persona persona = directorio.findPersonaByIdentificacion(identificacionPersona);

        // 2. Vinculamos la factura a esa persona
        factura.setPersona(persona);

        // 3. Guardamos
        return facturaRepository.save(factura);
    }

    public List<Factura> findFacturasByPersona(String identificacion) {
        Persona persona = directorio.findPersonaByIdentificacion(identificacion);
        return persona.getFacturas(); // Devuelve la lista gracias a la relación @OneToMany
    }
}