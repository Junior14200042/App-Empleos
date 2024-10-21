package com.devjr.app_empleo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
    private Integer id;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private Integer estatus;
    private Date fechaRegistro;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="UsuarioPerfil",
            joinColumns = @JoinColumn(name="idUsuario"),
            inverseJoinColumns = @JoinColumn(name="idPerfil")
    )
    private List<Perfil> perfiles;

    public void agregar(Perfil tempPerfil) {
        if (perfiles == null) {
            perfiles = new LinkedList<Perfil>();
        }
        perfiles.add(tempPerfil);
    }
}
