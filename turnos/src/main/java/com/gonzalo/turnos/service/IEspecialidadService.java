package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.Especialidad;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadService {

    List<Especialidad> listarEspecialidades();

    Optional<Especialidad> buscarPorId(Long id);

    Optional<Especialidad> buscarPorNombre(String nombre);

    Especialidad crearEspecialidad(Especialidad especialidad);

    void eliminarEspecialidad(Long id);
}
