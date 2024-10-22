package com.devjr.app_empleo.services;

import com.devjr.app_empleo.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriasServicesImpl implements IcategoriasService {


    List<Categoria> listCategorias=null;

    public CategoriasServicesImpl(){

        listCategorias= new LinkedList<>();

        listCategorias.add(Categoria.builder()
                .id(1)
                .nombre("Recursos Humanos")
                .descripcion("Trabajos relacionados con el area de RH.")
                .build());

        listCategorias.add(Categoria.builder()
                .id(2)
                .nombre("Ventas")
                .descripcion("Ofertas de trabajo relacionado con ventas.")
                .build());

        listCategorias.add(Categoria.builder()
                .id(3)
                .nombre("Sistemas")
                .descripcion("Trabajos relacionados con el area de TI.")
                .build());

        listCategorias.add(Categoria.builder()
                .id(4)
                .nombre("Software")
                .descripcion("Trabajos relacionados con el area de Desarrollo.")
                .build());


    }

    @Override
    public List<Categoria> listCategorias() {
        return listCategorias;
    }

    @Override
    public void guardar(Categoria categoria) {
         listCategorias.add(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        System.out.println("se eliminó");
    }
}
