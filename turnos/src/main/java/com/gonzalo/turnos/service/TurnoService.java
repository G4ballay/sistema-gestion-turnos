package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.Turno;
import com.gonzalo.turnos.repository.TurnoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TurnoService implements  ITurnoService{

    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public List<Turno> buscarPorProfesional(Long profesionalId) {
        return turnoRepository.findByProfesionalId(profesionalId);
    }

    @Override
    public List<Turno> buscarPorPaciente(Long pacienteId) {
        return turnoRepository.findByPacienteId(pacienteId);
    }

    @Override
    public List<Turno> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return turnoRepository.findByFechaHoraBetween(inicio,fin);
    }

    @Override
    public Turno crearTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public void cancelarTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}
