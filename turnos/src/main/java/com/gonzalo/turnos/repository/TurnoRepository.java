package com.gonzalo.turnos.repository;

import com.gonzalo.turnos.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
