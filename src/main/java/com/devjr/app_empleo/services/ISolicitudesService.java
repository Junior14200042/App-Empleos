package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISolicitudesService {

    void guardar(Solicitud solicitud);

    List<Solicitud> buscarTodas();

    void eliminar(Integer id);

    Solicitud buscarPorId(Integer id);

    Page<Solicitud> buscarTodas(Pageable page);

}
