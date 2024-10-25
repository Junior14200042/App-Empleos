package com.devjr.app_empleo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Vacantes")
@Entity
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date fecha;
    private Double salario;
    private Integer destacado;
    private String imagen;
    private String estatus;
    private String detalles;
    @OneToOne()
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;


    public void reset(){
        this.imagen=null;
    }

}
