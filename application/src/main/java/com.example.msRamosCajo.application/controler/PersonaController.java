package com.example.msRamosCajo.application.controler;


import com.example.msRamosCajo.domain.agregates.dto.PersonaDTO;
import com.example.msRamosCajo.domain.agregates.request.RequestPersona;
import com.example.msRamosCajo.domain.ports.PersonaServiceIn;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "API-PERSONA",
                version = "1.0",
                description = "Mantenimiento de una Persona"
        )
)
@RestController
@RequestMapping("/v2/persona")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;
    @Operation(summary = "Crea una Persona")
    @PostMapping
    public ResponseEntity<PersonaDTO> registrar(@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(requestPersona));
    }

    @Operation(summary = "Obtiene una Persona por id")
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO>obtenerPersona(@PathVariable Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerPersonaIn(id).get());

    }
    @Operation(summary = "Obtiene todas las personas")
    @GetMapping()
    public ResponseEntity<List<PersonaDTO>>obtenerTodos(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.obtenerTodosIn());

    }
    @Operation(summary = "Actualizar una Persona")
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO>actualizar(@PathVariable Long id,@RequestBody RequestPersona requestPersona){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaServiceIn.actualizarIn(id,requestPersona));

    }
}

