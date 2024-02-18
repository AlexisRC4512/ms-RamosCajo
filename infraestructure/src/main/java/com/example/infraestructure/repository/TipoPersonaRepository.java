package com.example.infraestructure.repository;


import com.example.infraestructure.entity.TipoPersonaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPersonaRepository extends JpaRepository<TipoPersonaEntity,Long> {
    TipoPersonaEntity findByCodTipo(@Param("codTipo") String codTipo);
}
