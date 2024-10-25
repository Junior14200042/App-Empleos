package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Usuario;
import com.devjr.app_empleo.model.Vacante;
import com.devjr.app_empleo.services.IUsuarioService;
import com.devjr.app_empleo.services.IVacantesServices;
import com.devjr.app_empleo.services.IcategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    IcategoriasService icategoriasService;

    @Autowired
    IVacantesServices vacantesServices;

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> lista= vacantesServices.buscarTodas();
        model.addAttribute("vacantes",lista);
        return "tabla";
    }

    @GetMapping("/")
    public String home(Model model){

        return "home";
    }

    @GetMapping("/signup")
    public String registrarse(Usuario usuario){
        return "usuarios/formUsuario";
    }
    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes redirectAttributes){



        usuario.setFechaRegistro(new Date());
        usuario.setEstatus(1);
        redirectAttributes.addFlashAttribute("msg","Se agregó correctamente");
        usuarioService.guardar(usuario);

        return "redirect:/usuarios/index";
    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Vacante vacante, Model model){


        ExampleMatcher matcher=ExampleMatcher
                .matching().withMatcher("descripcion",ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Vacante> example=Example.of(vacante,matcher);
        System.out.println(vacante);
        List<Vacante> lista = vacantesServices.buscarByExample(example);
        model.addAttribute("vacantes",lista);
        return "home";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @ModelAttribute
    public void setGenericos(Model model){

        model.addAttribute("vacantes",vacantesServices.buscarDestacadas());
        model.addAttribute("categorias",icategoriasService.listCategorias());
        model.addAttribute("search",new Vacante());
    }

}
