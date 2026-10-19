package com.example.demo;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaginaController {


    @GetMapping("/")
    public String cargarFormularioInicial(Model model) {
        model.addAttribute("personasFormulario", new PersonasFormulario());
        return "formulario"; 
    }

    @PostMapping("/paso1")
    public String procesarPaso1(@Valid @ModelAttribute("personasFormulario") PersonasFormulario personasFormulario,
                                BindingResult result,
                                Model model) {
        if (personasFormulario.getFechaNacimiento() != null && personasFormulario.getEdad() != null) {
            int edadCalculada = Period.between(personasFormulario.getFechaNacimiento(), LocalDate.now()).getYears();
            if (edadCalculada != personasFormulario.getEdad()) {
                result.rejectValue("edad", "error.edad", "La edad no coincide con la fecha de nacimiento.");
            }
        }

        if (result.hasFieldErrors("nombre") || result.hasFieldErrors("edad") || result.hasFieldErrors("fechaNacimiento")) {
            return "formulario";
        }

        return "paso2";
    }

    @PostMapping("/paso2")
    public String procesarPaso2(@Valid @ModelAttribute("personasFormulario") PersonasFormulario personasFormulario,
                                BindingResult result,
                                Model model) {

        if (result.hasFieldErrors("consola")) {
            return "paso2";
        }

        return "paso3";
    }


    @PostMapping("/paso3")
    public String procesarPaso3(@Valid @ModelAttribute("personasFormulario") PersonasFormulario personasFormulario,
                                BindingResult result,
                                Model model) {

        if (result.hasFieldErrors("juegos")) {
            return "paso3";
        }

        List<String> listaResultados = new ArrayList<>();
        listaResultados.add("Paso 1 - Nombre: " + personasFormulario.getNombre() 
                + "  Edad: " + personasFormulario.getEdad() 
                + "  Fecha de Nacimiento: " + personasFormulario.getFechaNacimiento());
        listaResultados.add("Consola : " + personasFormulario.getConsola());
        listaResultados.add("Juegos : " + String.join(", ", personasFormulario.getJuegos()));

        model.addAttribute("listaResultados", listaResultados);

        return "resultado";
    }
}