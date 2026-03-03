package com.gonzalo.turnos.repository;

import com.gonzalo.turnos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByProfesionalId(Long profesionalId);

    List<Turno> findByPacienteId(Long pacienteId);

    List<Turno> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

    boolean existsByProfesionalIdAndFechaHora(Long profesionalId, LocalDateTime fechaHora);
}
