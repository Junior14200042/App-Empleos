package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Categoria;
import com.devjr.app_empleo.services.CategoriasServicesImpl;
import com.devjr.app_empleo.services.IcategoriasService;
import com.devjr.app_empleo.services.VacantesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    IcategoriasService categoriasServices;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String mostrarIndex(Model model){

        List<Categoria> lista= categoriasServices.listCategorias();

        model.addAttribute("categorias",lista);

        return "categorias/listCategorias";
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String crear(){

        return "categorias/formCategoria";
    }



    @PostMapping("/save")
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            for (ObjectError errores : result.getAllErrors()){
                System.out.println(errores);
            }
            return "categorias/formCategoria";
        }
        attributes.addFlashAttribute("msg","Se guardó correctamente");
        categoriasServices.guardar(categoria);

        return "redirect:/categorias/index";
    }
}
