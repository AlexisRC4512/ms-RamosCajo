package com.example.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@NamedQuery(name = "TipoPersonaEntity.findByCodTipo", query = "select a from TipoPersonaEntity a where a.codTipo=:codTipo")
@Entity
@Table(name = "tipo_persona")
@Getter
@Setter
public class TipoPersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_persona ")
    private Long idTipoPersona;
    @Column(name = "cod_tipo", nullable = false, length = 45)
    private String codTipo;
    @Column(name = "desc_tipo", nullable = false, length = 45)
    private String descTipo;
    @Column(name = "estado", nullable = false)
    private Integer estado;
    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;
    @Column(name = "date_create")
    private Timestamp dateCreate;
    @Column(name = "usua_modif", length = 45)
    private String usuaModif;
    @Column(name = "date_modif")
    private Timestamp dateModif;
    @Column(name = "usua_delet", length = 45)
    private String usuaDelet;
    @Column(name = "date_delet")
    private Timestamp dateDelet;
}
