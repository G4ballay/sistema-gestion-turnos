package com.gonzalo.turnos.repository;

import com.gonzalo.turnos.entity.EstadoTurno;
import com.gonzalo.turnos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByProfesionalId(Long profesionalId);

    List<Turno> findByUsuarioId(Long pacienteId);

    List<Turno> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Turno> findByProfesionalEspecialidadId(Long especialidadId);

    List<Turno> findByProfesionalEspecialidadIdOrderByFechaHoraAsc(Long especialidadId, EstadoTurno estado, LocalDateTime fecha);

    List<Turno> findByProfesionalEspecialidadIdAndEstadoAndFechaHoraBetweenOrderByFechaHoraAsc(Long especialidadId, EstadoTurno estado, LocalDateTime inicio, LocalDateTime fin);

    List<Turno> findByProfesionalIdAndEstadoOrderByFechaHoraAsc(Long profesionalId, EstadoTurno estado, LocalDateTime fecha);

    boolean existsByProfesionalIdAndFechaHora(Long profesionalId, LocalDateTime fechaHora);
}
