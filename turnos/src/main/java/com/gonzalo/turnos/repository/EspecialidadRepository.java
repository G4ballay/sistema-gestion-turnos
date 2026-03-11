package com.gonzalo.turnos.repository;

import com.gonzalo.turnos.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    List<Especialidad> findByNombreStartingWithIgnoreCase(String nombre);

    Optional<Especialidad> findByNombreIgnoreCase(String nombre);
}
