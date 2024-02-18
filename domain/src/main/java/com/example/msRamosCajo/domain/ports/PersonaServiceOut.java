package com.example.msRamosCajo.domain.ports;

import com.example.msRamosCajo.domain.agregates.dto.PersonaDTO;
import com.example.msRamosCajo.domain.agregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {
    PersonaDTO crearPersonaOut(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaOut(Long id);
    List<PersonaDTO> obtenerTodosOut();
    PersonaDTO actualizarOut(Long id, RequestPersona requestPersona);
    PersonaDTO deleteOut(Long id);
}
