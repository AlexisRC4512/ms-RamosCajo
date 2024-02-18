package com.example.msRamosCajo.domain.impl;

import com.example.msRamosCajo.domain.agregates.dto.PersonaDTO;
import com.example.msRamosCajo.domain.agregates.request.RequestPersona;
import com.example.msRamosCajo.domain.ports.PersonaServiceIn;
import com.example.msRamosCajo.domain.ports.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonsaServiceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;
    @Override
    public PersonaDTO crearPersonaIn(RequestPersona requestPersona) {
        return personaServiceOut.crearPersonaOut(requestPersona);
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaIn(Long id) {
        return personaServiceOut.obtenerPersonaOut(id);
    }

    @Override
    public List<PersonaDTO> obtenerTodosIn() {
        return personaServiceOut.obtenerTodosOut();
    }

    @Override
    public PersonaDTO actualizarIn(Long id, RequestPersona requestPersona) {
        return personaServiceOut.actualizarOut(id,requestPersona);
    }

    @Override
    public PersonaDTO deleteIn(Long id) {
        return personaServiceOut.deleteOut(id);
    }
}
