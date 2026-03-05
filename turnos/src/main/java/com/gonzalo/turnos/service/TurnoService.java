package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.EstadoTurno;
import com.gonzalo.turnos.entity.Profesional;
import com.gonzalo.turnos.entity.Turno;
import com.gonzalo.turnos.entity.User;
import com.gonzalo.turnos.exeption.*;
import com.gonzalo.turnos.repository.ProfesionalRepository;
import com.gonzalo.turnos.repository.TurnoRepository;
import com.gonzalo.turnos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements  ITurnoService{

    private final TurnoRepository turnoRepository;
    private final ProfesionalRepository profesionalRepository;
    private final UserRepository userRepository;

    public TurnoService(TurnoRepository turnoRepository,
                            ProfesionalRepository profesionalRepository,
                            UserRepository userRepository) {
        this.turnoRepository = turnoRepository;
        this.profesionalRepository = profesionalRepository;
        this.userRepository = userRepository;
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
    public Turno crearTurno(Long profesionalId, LocalDateTime fechaHora) {

        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new TurnoYaPasoException("No se pueden crear turnos en el pasado");
        }

        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new ProfesionNoEncontradoException("Profesional no encontrado"));


        if (turnoRepository.existsByProfesionalIdAndFechaHora(profesionalId, fechaHora)) {
            throw new TurnoYaCreadoException("Ya existe un turno para ese profesional en ese horario");
        }



        Turno turno = new Turno();

        turno.setProfesional(profesional);
        turno.setFechaHora(fechaHora);
        turno.setCreatedAt(LocalDateTime.now());
        turno.setEstado(EstadoTurno.DISPONIBLE);

        return turnoRepository.save(turno);
    }

    @Override
    public Turno asignarTurno(Long turnoId, Long pacienteId) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new TurnoNoExisteException("Turno no encontrado"));

        if(turno.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new TurnoYaPasoException("Este turno ya expiro");
        }

        if(turno.getEstado() != EstadoTurno.DISPONIBLE) {
            throw new TurnoNoDisponibleException("El turno no esta disponible");
        }

        User paciente = userRepository.findById(pacienteId)
                .orElseThrow(() -> new UsuarioNoExisteException("Paciente no encontrado"));

        turno.setUsuario(paciente);
        turno.setEstado(EstadoTurno.RESERVADO);

        return turnoRepository.save(turno);
    }

    @Override
    public Turno cancelarTurno(Long turnoId, Long pacienteId) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new TurnoNoExisteException("Turno seleccionado no existe"));

        if(turno.getEstado() == EstadoTurno.CANCELADO) {
            throw new TurnoYaCanceladoException("Este turno ya fue cancelado");
        }
        if (turno.getEstado() == EstadoTurno.DISPONIBLE) {
            throw new TurnoDisponibleException("El turno no está reservado");
        }
        if (turno.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new TurnoYaPasoException("No se puede cancelar un turno pasado");
        }
        if (turno.getFechaHora().minusHours(2).isBefore(LocalDateTime.now())) {
            throw new AnticipacionNoRespetadaException("No se puede cancelar con menos de 2 horas de anticipación");
        }
        if (turno.getUsuario() == null || !turno.getUsuario().getId().equals(pacienteId)) {
            throw new NoPermisoTurnoException("No tienes permiso para cancelar este turno");
        }

        LocalDate fechaTurno = turno.getFechaHora().toLocalDate();
        LocalDate hoy = LocalDate.now();

        if (fechaTurno.equals(hoy)) {
            turno.setEstado(EstadoTurno.CANCELADO);
        } else {
            turno.setEstado(EstadoTurno.DISPONIBLE);
            turno.setUsuario(null);
        }

        return turnoRepository.save(turno);
    }

    @Override
    public void borrarTurno(Long id) {
        if (!turnoRepository.existsById(id)) {
            throw new TurnoNoExisteException("El turno no existe");
        }

        turnoRepository.deleteById(id);
    }
}

