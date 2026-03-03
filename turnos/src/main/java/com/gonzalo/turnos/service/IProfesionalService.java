package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.Especialidad;
import com.gonzalo.turnos.entity.Profesional;
import java.util.List;
import java.util.Optional;

public interface IProfesionalService {
    List<Profesional> listarProfesionales();

    List<Profesional> buscarPorEspecialidad(long especialidadId);

    Optional<Profesional> buscarPorId(Long id);

    List<Profesional> buscarPorApellido(String apellido);

    List<Profesional> buscarPorNombre(String nombre);

    Profesional crearProfesional(Profesional profesional);

    void eliminarProfesional(Long id);
}
