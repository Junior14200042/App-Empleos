package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Categoria;

import java.util.LinkedList;
import java.util.List;

public interface IcategoriasService {

    List<Categoria> listCategorias();

    void guardar(Categoria categoria);

    Categoria buscarPorId(Integer id);

    void eliminar(Integer id);
}
