package com.example.msRamosCajo.domain.ports;

import com.example.msRamosCajo.domain.agregates.dto.PersonaDTO;
import com.example.msRamosCajo.domain.agregates.request.RequestPersona;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {
    PersonaDTO crearPersonaIn(RequestPersona requestPersona);
    Optional<PersonaDTO> obtenerPersonaIn(Long id);
    List<PersonaDTO> obtenerTodosIn();
    PersonaDTO actualizarIn(Long id, RequestPersona requestPersona);
    PersonaDTO deleteIn(Long id);
}

