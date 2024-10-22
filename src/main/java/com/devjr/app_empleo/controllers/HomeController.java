package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Usuario;
import com.devjr.app_empleo.model.Vacante;
import com.devjr.app_empleo.services.IUsuarioService;
import com.devjr.app_empleo.services.IVacantesServices;
import com.devjr.app_empleo.services.VacantesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @ModelAttribute
    public void setGenericos(Model model){

        model.addAttribute("vacantes",vacantesServices.buscarDestacadas());
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

}
