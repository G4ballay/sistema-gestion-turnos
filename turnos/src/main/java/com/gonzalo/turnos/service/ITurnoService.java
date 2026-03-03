package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.Profesional;
import com.gonzalo.turnos.entity.Turno;
import com.gonzalo.turnos.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ITurnoService {

    List<Turno> listarTurnos();

    Optional<Turno> buscarPorId(Long id);

    List<Turno> buscarPorProfesional(Long profesionalId);

    List<Turno> buscarPorPaciente(Long pacienteId);

    List<Turno> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin);

    Turno crearTurno(Turno turno);

    void cancelarTurno(Long id);
}
