package com.devjr.app_empleo.controllers;

import com.devjr.app_empleo.model.Categoria;
import com.devjr.app_empleo.model.Vacante;
import com.devjr.app_empleo.services.IVacantesServices;
import com.devjr.app_empleo.services.IcategoriasService;
import com.devjr.app_empleo.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Autowired
    IVacantesServices vacantesServices;

    @Autowired
    IcategoriasService icategoriasService;

    @Value("${empleosapp.ruta.imagenes}")
    public String ruta;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Vacante> lista = vacantesServices.buscarTodas();
        model.addAttribute("vacantes", lista);

        return "vacantes/listVacantes";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {

        List<Categoria> listaCategoria = icategoriasService.listCategorias();
        model.addAttribute("categorias", listaCategoria);

        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, Model model, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }

        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/img-vacantes/"; // Linux/MAC
           // String ruta = "c:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null) { // La imagen si se subio
            // Procesamos la variable nombreImagen
                vacante.setImagen(nombreImagen);
            }
        }
        System.out.println(vacante);
        vacantesServices.guardar(vacante);
        attributes.addFlashAttribute("msg", "Registro guardado exitosamente");
        return "redirect:/vacantes/index";

    }
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Integer id,Model model){
        Vacante vacante=vacantesServices.buscarPorId(id);
        model.addAttribute("vacante",vacante);
        model.addAttribute("categorias", icategoriasService.listCategorias());

        return "vacantes/formVacante";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Integer idVacante, Model model,RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg","Se eliminó con éxito");
        vacantesServices.eliminar(idVacante);
        return "redirect:/vacantes/index";
    }

    @GetMapping("view/{id}")
    public String verDetalle(@PathVariable("id") Integer idVacante, Model model) {

        Vacante vacante = vacantesServices.buscarPorId(idVacante);
        model.addAttribute("id", idVacante);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
    }

}
