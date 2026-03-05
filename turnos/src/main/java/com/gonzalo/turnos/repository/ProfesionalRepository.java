package com.gonzalo.turnos.repository;

import com.gonzalo.turnos.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    List<Profesional> findByEspecialidadId(Long especialidadId);

    List<Profesional> findByApellidoStartingWithIgnoreCase(String apellido);

    Optional<Profesional> findByEmail(String email);

    List<Profesional> findByNombreStartingWithIgnoreCase(String nombre);
}
