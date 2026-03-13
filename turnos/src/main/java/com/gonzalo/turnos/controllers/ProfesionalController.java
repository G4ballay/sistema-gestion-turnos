package com.gonzalo.turnos.controllers;


import com.gonzalo.turnos.entity.Profesional;
import com.gonzalo.turnos.service.ProfesionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesionales")
public class ProfesionalController {

    private final ProfesionalService profesionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @GetMapping
    public List<Profesional> listarProfesionales() {
        return profesionalService.listarProfesionales();
    }

    @GetMapping("/{id}")
    public Optional<Profesional> buscarPorId(@PathVariable long id) {
        return profesionalService.buscarPorId(id);
    }

    @GetMapping("/especialidad/{id}")
    public List<Profesional> buscarPorEspecialidad(@PathVariable long id) {
        return profesionalService.buscarPorEspecialidad(id);
    }

    @GetMapping("/buscar/nombre")
    public List<Profesional> buscarPorNombre(@RequestParam String nombre) {
        return profesionalService.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar/apellido")
    public List<Profesional> buscarPorApellido(@RequestParam String apellido) {
        return profesionalService.buscarPorApellido(apellido);
    }
}
