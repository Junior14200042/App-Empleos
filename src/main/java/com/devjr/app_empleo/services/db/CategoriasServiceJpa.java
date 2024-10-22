package com.devjr.app_empleo.services.db;

import com.devjr.app_empleo.model.Categoria;
import com.devjr.app_empleo.repository.CategoriasRepository;
import com.devjr.app_empleo.services.IcategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoriasServiceJpa implements IcategoriasService {

    @Autowired
    CategoriasRepository categoriasRepository;

    @Override
    public List<Categoria> listCategorias() {
        return categoriasRepository.findAll();
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriasRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> categoria= categoriasRepository.findById(id);
        if(categoria.isPresent()){
            return categoria.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        categoriasRepository.deleteById(id);
    }


}
