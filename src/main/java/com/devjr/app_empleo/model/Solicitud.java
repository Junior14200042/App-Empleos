package com.devjr.app_empleo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private String comentarios;
    private String archivo;

    @OneToOne
    @JoinColumn(name="idVacante")
    private Vacante vacante;


    @OneToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;


}
