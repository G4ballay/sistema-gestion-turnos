package com.gonzalo.turnos.repository;

import com.gonzalo.turnos.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    List<Profesional> findByEspecialidadId(Long especialidadId);

    List<Profesional> findByApellidoStartingWithIgnoreCase(String apellido);

    List<Profesional> findByNombreStartingWithIgnoreCase(String nombre);
}
