package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVacantesServices {
    List<Vacante> buscarTodas();

    Vacante buscarPorId(Integer idVacante);

    void guardar(Vacante vacante);

    List<Vacante> buscarDestacadas();

    void eliminar(Integer idVacante);

    List<Vacante> buscarByExample(Example<Vacante> example);
    Page<Vacante> buscarTodas(Pageable page);
}
