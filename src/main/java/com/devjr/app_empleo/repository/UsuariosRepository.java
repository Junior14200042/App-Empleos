package com.devjr.app_empleo.repository;

import com.devjr.app_empleo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
