package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Vacante;

import java.util.List;

public interface IVacantesServices {
    List<Vacante> buscarTodas();

    Vacante buscarPorId(Integer idVacante);

    void guardar(Vacante vacante);

    List<Vacante> buscarDestacadas();

    void eliminar(Integer idVacante);
}
