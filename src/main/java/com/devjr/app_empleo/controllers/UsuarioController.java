package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Usuario;
import com.devjr.app_empleo.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/usuarios")
@Controller
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/index")
    public String mostrarIndex(Model model){
        List<Usuario> usuarios = usuarioService.buscarTodos();
        model.addAttribute("usuarios",usuarios);
        return "usuarios/listUsuarios";

    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("msg","Se eliminó el registro");

        usuarioService.eliminar(id);

        return "redirect:/usuarios/index";
    }
}
