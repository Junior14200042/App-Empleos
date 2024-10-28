package com.devjr.app_empleo.services.db;

import com.devjr.app_empleo.model.Solicitud;
import com.devjr.app_empleo.repository.SolicitudesRepository;
import com.devjr.app_empleo.services.ISolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesServiceImpl implements ISolicitudesService {

    @Autowired
    SolicitudesRepository solicitudesRepository;

    @Override
    public void guardar(Solicitud solicitud) {
        solicitudesRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> buscarTodas() {
        return solicitudesRepository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        solicitudesRepository.deleteById(id);
    }

    @Override
    public Solicitud buscarPorId(Integer id) {

        Optional<Solicitud> solicitud = solicitudesRepository.findById(id);

        if(solicitud.isPresent()){
            return solicitud.get();
        }

        return null;
    }

    @Override
    public Page<Solicitud> buscarTodas(Pageable page) {

        return solicitudesRepository.findAll(page);
    }
}
