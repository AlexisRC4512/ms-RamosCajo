package com.example.infraestructure.repository;


import com.example.infraestructure.entity.TipoDocumentoEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {
    TipoDocumentoEntity findByCodTipo(@Param("codTipo") String codTipo);
}
