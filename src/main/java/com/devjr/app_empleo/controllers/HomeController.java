package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Vacante;
import com.devjr.app_empleo.services.IVacantesServices;
import com.devjr.app_empleo.services.VacantesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IVacantesServices vacantesServices;

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> lista= vacantesServices.buscarTodas();
        model.addAttribute("vacantes",lista);
        return "tabla";
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model){
        Vacante vacante = Vacante.builder()
                .nombre("Ingeniero de Software")
                .descripcion("Se solicita desarrollador Senior en Java")
                .fecha(new Date())
                .salario(5600.0)
                .build();
        model.addAttribute("vacante", vacante);

        return "detalle";
    }

    @GetMapping("/")
    public String home(Model model){

       /* List<Vacante> lista= vacantesServices.buscarTodas();
        model.addAttribute("vacantes",lista);
        System.out.println(vacantesServices.buscarDestacadas());
        model.addAttribute("vacantes",vacantesServices.buscarDestacadas());*/
        return "home";
    }
    @ModelAttribute
    public void setGenericos(Model model){

        model.addAttribute("vacantes",vacantesServices.buscarDestacadas());
    }
}
