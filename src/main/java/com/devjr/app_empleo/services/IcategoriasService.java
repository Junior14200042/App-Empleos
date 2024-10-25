package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

public interface IcategoriasService {

    List<Categoria> listCategorias();

    void guardar(Categoria categoria);

    Categoria buscarPorId(Integer id);

    void eliminar(Integer id);

    Page<Categoria> indexPagination(Pageable page);
}
