package com.gonzalo.turnos.controllers;

import com.gonzalo.turnos.entity.Especialidad;
import com.gonzalo.turnos.service.EspecialidadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public List<Especialidad> listarEspecialidades(){
        return especialidadService.listarEspecialidades();
    }

    @GetMapping("/buscar")
    public List<Especialidad> buscarPorNombre(@RequestParam String nombre){
        return especialidadService.buscarPorNombre(nombre);
    }
}
