package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Solicitud;
import com.devjr.app_empleo.model.Usuario;
import com.devjr.app_empleo.model.Vacante;
import com.devjr.app_empleo.services.EmailService;
import com.devjr.app_empleo.services.ISolicitudesService;
import com.devjr.app_empleo.services.IUsuarioService;
import com.devjr.app_empleo.services.IVacantesServices;
import com.devjr.app_empleo.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Autowired
    IVacantesServices vacantesServices;

    @Value("${empleosapp.ruta.cv}")
    private String rutaCV;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ISolicitudesService solicitudesService;

    @Autowired
    private EmailService emailService;



    @GetMapping("/create/{idVacante}")
    public String crear(@PathVariable("idVacante") Integer idVacante, Model model, Solicitud solicitud){

        Vacante vacante = vacantesServices.buscarPorId(idVacante);
        System.out.println(vacante);
        model.addAttribute("vacante",vacante);

        return "solicitudes/formSolicitud";
    }

    @PostMapping("/save")
    public String guardar(Solicitud solicitud, BindingResult result, @RequestParam("archivoCV") MultipartFile multipartFile,
                          Authentication auth, RedirectAttributes attributes){
        System.out.println(solicitud);

        if(result.hasErrors()){
            System.out.println("Hay errores");
            return "solicitudes/formSolicitud";
        }

        if(!multipartFile.isEmpty()){
            String nombreArchivo = Utileria.guardarArchivo(multipartFile,rutaCV);
            System.out.println(nombreArchivo);
            if(nombreArchivo!=null){
                solicitud.setArchivo(nombreArchivo);
                System.out.println(nombreArchivo);
            }
        }

        String username= auth.getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);

        solicitud.setUsuario(usuario);
        solicitud.setFecha(new Date());

        solicitudesService.guardar(solicitud);
        attributes.addFlashAttribute("msg","Gracias por enviar tu CV");
        System.out.println(solicitud);

        return "redirect:/";
    }


    @GetMapping("/indexPaginate")
    public String indexPaginate(Model model, Pageable page){

        Page<Solicitud> solicitudes = solicitudesService.buscarTodas(page);
        model.addAttribute("lista",solicitudes);


        return "solicitudes/listSolicitudes";
    }

    @GetMapping("/delete/{id}")
    public String eliminarPorId(@PathVariable Integer id, RedirectAttributes redirectAttributes){

        solicitudesService.eliminar(id);
        redirectAttributes.addFlashAttribute("msg","Se eliminó con éxito");
        return "redirect:/solicitudes/indexPaginate";
    }

    @GetMapping("/correo-form/{id}")
    public String mostrarFormulario(Model model, @PathVariable Integer id) {

        Solicitud solicitud = solicitudesService.buscarPorId(id);
        model.addAttribute("solicitud",solicitud);


        return "correo-form"; // Nombre del archivo HTML sin extensión
    }

    @PostMapping("/enviar-correo")
    public String enviarCorreo(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message,
            RedirectAttributes redirectAttributes) {
        emailService.sendEmail(to, subject, message);

        System.out.println(to + " "+" "+subject);
        redirectAttributes.addFlashAttribute("msg", "Correo enviado con éxito.");
        return "redirect:/solicitudes/indexPaginate"; // Puedes redirigir a otra página o mostrar un mensaje

    }

}
