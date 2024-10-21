package com.devjr.app_empleo.repository;

import com.devjr.app_empleo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}
