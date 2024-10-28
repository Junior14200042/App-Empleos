package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Perfil;
import com.devjr.app_empleo.model.Usuario;
import com.devjr.app_empleo.model.Vacante;
import com.devjr.app_empleo.services.IUsuarioService;
import com.devjr.app_empleo.services.IVacantesServices;
import com.devjr.app_empleo.services.IcategoriasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String encriptar(@PathVariable("texto") String texto){
        return texto+" Encriptado en "+passwordEncoder.encode(texto);
    }
    @GetMapping("/login")
    public String mostrarLogin(){
        return "formLogin";
    }


    @GetMapping("/index")
    public String mostrarIndex(Authentication auth, HttpSession session){

        String username=auth.getName();
        System.out.println(username);

        for(GrantedAuthority rol : auth.getAuthorities()){
            System.out.println("ROL: "+ rol.getAuthority());
        }

        if(session.getAttribute("usuario")==null){

            Usuario usuario = usuarioService.buscarPorUsername(username);
          //  usuario.setPassword(null);
            System.out.println("usuario: "+usuario);
            session.setAttribute("usuario",usuario);

        }
        return "redirect:/";

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

        String passUsuario = usuario.getPassword();
        String encriptPass = passwordEncoder.encode(passUsuario);

        usuario.setPassword(encriptPass);
        usuario.setFechaRegistro(new Date());
        usuario.setEstatus(1);

        Perfil perfil = new Perfil();
        perfil.setId(3);
        usuario.agregar(perfil);

       /* redirectAttributes.addFlashAttribute("msg","Se agregó correctamente");*/
        usuarioService.guardar(usuario);

        return "redirect:/login";
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
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request,null,null);
        return "redirect:/";
    }

}
