package com.devjr.app_empleo.services.db;

import com.devjr.app_empleo.model.Vacante;
import com.devjr.app_empleo.repository.VacantesRepository;
import com.devjr.app_empleo.services.IVacantesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesServices {

    @Autowired
    VacantesRepository vacantesRepository;

    @Override
    public List<Vacante> buscarTodas() {

        return vacantesRepository.findAll();
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {

        Optional<Vacante> vacante = vacantesRepository.findById(idVacante);

        if(vacante.isPresent()){
            return vacante.get();
        }

        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantesRepository.save(vacante);
    }

    @Override
    public List<Vacante> buscarDestacadas() {
        return vacantesRepository.findByDestacadoAndEstatusOrderByIdDesc(1,"Aprobada");
    }

    @Override
    public void eliminar(Integer idVacante) {
        vacantesRepository.deleteById(idVacante);
    }

    @Override
    public List<Vacante> buscarByExample(Example<Vacante> example) {
        return vacantesRepository.findAll(example);
    }

    @Override
    public Page<Vacante> buscarTodas(Pageable page) {
        return vacantesRepository.findAll(page);
    }
}
