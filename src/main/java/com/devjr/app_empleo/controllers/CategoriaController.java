package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Categoria;
import com.devjr.app_empleo.services.IcategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    IcategoriasService categoriasServices;

    @GetMapping("/indexPaginate")
    public String indexPaginado(Model model, Pageable page){

        Page<Categoria> listaCategorias= categoriasServices.indexPagination(page);
        model.addAttribute("categorias",listaCategorias);

        return "categorias/listCategorias";
    }


    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String crear(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categorias/formCategoria";
    }


    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Integer id, Model model ){

        Categoria categoria= categoriasServices.buscarPorId(id);
        model.addAttribute("categoria",categoria);


        return "categorias/formCategoria";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("msg","Se eliminó correctamente");
        categoriasServices.eliminar(id);
        return "redirect:/categorias/indexPaginate";
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

        return "redirect:/categorias/indexPaginate";
    }
}
