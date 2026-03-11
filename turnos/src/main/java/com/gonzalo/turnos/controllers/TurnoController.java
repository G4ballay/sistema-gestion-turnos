package com.gonzalo.turnos.controllers;

import com.gonzalo.turnos.entity.Turno;
import com.gonzalo.turnos.service.TurnoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/generar")
    public Turno generarTurnos(
            @RequestParam Long profesionalId,
            @RequestParam LocalDateTime fecha) {

        return turnoService.crearTurno(profesionalId, fecha);
    }

    @PostMapping("/{turnoId}/reservar")
    public Turno reservarTurno(
            @PathVariable Long turnoId,
            @RequestParam Long pacienteId) {

        return turnoService.asignarTurno(turnoId, pacienteId);
    }

    @PostMapping("/{id}/cancelar")
    public void cancelarTurno(
            @PathVariable Long turnoId,
            @RequestParam Long pacienteId) {
        turnoService.cancelarTurno(turnoId, pacienteId);
    }

    @GetMapping("/disponibles")
    public List<Turno> buscarTurnosDisponibles(
            @RequestParam Long especialidadId) {

        return turnoService.buscarTurnoDisponibles(especialidadId);
    }

    @GetMapping("/especialidad/{id}")
    public List<Turno> turnosPorEspecialidadYFecha(
            @PathVariable Long id,
            @RequestParam LocalDate fecha){

        return turnoService.turnosDisponiblesPorEspecialidadYFecha(id, fecha);
    }

    @GetMapping("/profesional/{id}/disponibles")
    public List<Turno> turnosDisponiblesPorProfesional(@PathVariable Long id){
        return turnoService.turnosDisponiblesPorProfesional(id);
    }

}

