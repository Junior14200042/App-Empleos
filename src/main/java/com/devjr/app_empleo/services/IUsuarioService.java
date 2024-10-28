package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> buscarTodos();

    void guardar(Usuario usuario);

   // Usuario buscarPorId(Integer id);

    void eliminar(Integer id);

    Usuario buscarPorUsername(String username);

}
