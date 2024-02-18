package com.example.infraestructure.adapters;



import com.example.infraestructure.entity.PersonaEntity;
import com.example.infraestructure.entity.TipoDocumentoEntity;
import com.example.infraestructure.entity.TipoPersonaEntity;
import com.example.infraestructure.mapper.PersonaMapper;
import com.example.infraestructure.redis.RedisService;
import com.example.infraestructure.repository.PersonaRepository;
import com.example.infraestructure.repository.TipoDocumentoRepository;
import com.example.infraestructure.repository.TipoPersonaRepository;
import com.example.infraestructure.rest.client.ClienteReniec;
import com.example.infraestructure.util.Util;
import com.example.msRamosCajo.domain.agregates.constants.Constants;
import com.example.msRamosCajo.domain.agregates.dto.PersonaDTO;
import com.example.msRamosCajo.domain.agregates.request.RequestPersona;
import com.example.msRamosCajo.domain.agregates.response.ResponseReniec;
import com.example.msRamosCajo.domain.ports.PersonaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoPersonaRepository tipoPersonaRepository;
    private final PersonaMapper personaMapper;
    private final ClienteReniec reniec;
    private final RedisService redisService;
    private final Util util;
    @Value("${token.api}")
    private String tokenApi;
    @Override
    public PersonaDTO crearPersonaOut(RequestPersona requestPersona) {
        ResponseReniec datosReniec = getExecutionReniec(requestPersona.getNumDoc());
        personaRepository.save(getEntity(datosReniec,requestPersona));
        return personaMapper.mapToDto(getEntity(datosReniec,requestPersona));
    }

    @Override
    public Optional<PersonaDTO> obtenerPersonaOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_KEY_PERSONA+id);
        if(redisInfo != null){
            PersonaDTO personaDTO = util.convertFromJson(redisInfo, PersonaDTO.class);
            return Optional.of(personaDTO);
        }else{
            PersonaDTO dto = personaMapper.mapToDto(personaRepository.findById(id).get());
            String redis = util.convertToJson(dto);
            redisService.saveInRedis(Constants.REDIS_KEY_PERSONA+id,redis,1);
            return Optional.of(dto);
        }
    }

    @Override
    public List<PersonaDTO> obtenerTodosOut() {
        List<PersonaDTO> personaDTOList = new ArrayList<>();
        List<PersonaEntity> entities = personaRepository.findAll();
        for(PersonaEntity persona : entities){
            PersonaDTO personaDTO = personaMapper.mapToDto(persona);
            personaDTOList.add(personaDTO);
        }
        return personaDTOList;
    }

    @Override
    public PersonaDTO actualizarOut(Long id, RequestPersona requestPersona) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            ResponseReniec responseReniec = getExecutionReniec(requestPersona.getNumDoc());
            personaRepository.save(getEntityUpdate(responseReniec,entity.get()));
            return personaMapper.mapToDto(getEntityUpdate(responseReniec,entity.get()));
        }
        return null;
    }

    @Override
    public PersonaDTO deleteOut(Long id) {
        boolean existe = personaRepository.existsById(id);
        if(existe){
            Optional<PersonaEntity> entity = personaRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            personaRepository.save(entity.get());
            return personaMapper.mapToDto(entity.get());
        }
        return null;
    }

    public ResponseReniec getExecutionReniec(String numero){
        String authorization = "Bearer "+tokenApi;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero,authorization);
        return  responseReniec;
    }
    private PersonaEntity getEntity(ResponseReniec reniec, RequestPersona requestPersona){
        TipoDocumentoEntity tipoDocumento = tipoDocumentoRepository.findByCodTipo(requestPersona.getTipoDoc());
        TipoPersonaEntity tipoPersona = tipoPersonaRepository.findByCodTipo(requestPersona.getTipoPer());
        PersonaEntity entity = new PersonaEntity();
        entity.setNumDocu(reniec.getNumeroDocumento());
        entity.setNombres(reniec.getNombres());
        entity.setApePat(reniec.getApellidoPaterno());
        entity.setApeMat(reniec.getApellidoMaterno());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setTipoDocumento(tipoDocumento);
        entity.setTipoPersona(tipoPersona);
        return entity;
    }
    private PersonaEntity getEntityUpdate(ResponseReniec reniec, PersonaEntity personaActualizar){
        personaActualizar.setNombres(reniec.getNombres());
        personaActualizar.setApePat(reniec.getApellidoPaterno());
        personaActualizar.setApeMat(reniec.getApellidoMaterno());
        personaActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        personaActualizar.setDateModif(getTimestamp());
        return personaActualizar;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}

