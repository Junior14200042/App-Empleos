package com.devjr.app_empleo.repository;

import com.devjr.app_empleo.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudesRepository  extends JpaRepository<Solicitud,Integer> {
}
