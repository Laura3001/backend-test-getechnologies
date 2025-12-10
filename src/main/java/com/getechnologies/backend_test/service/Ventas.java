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
    private Directorio directorio;

    public Factura storeFactura(String identificacionPersona, Factura factura) {
        // Busca a la persona (si no existe, el servicio Directorio lanza error 404)
        Persona persona = directorio.findPersonaByIdentificacion(identificacionPersona);

        // Vincula la factura a la persona
        factura.setPersona(persona);

        // Guardar
        return facturaRepository.save(factura);
    }

    public List<Factura> findFacturasByPersona(String identificacion) {
        Persona persona = directorio.findPersonaByIdentificacion(identificacion);
        return persona.getFacturas(); // Devuelve la lista
    }
}