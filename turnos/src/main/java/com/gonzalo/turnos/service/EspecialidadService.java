package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.Especialidad;
import com.gonzalo.turnos.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements IEspecialidadService{

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAll();
    }

    @Override
    public Optional<Especialidad> buscarPorId(Long id) {
        return especialidadRepository.findById(id);
    }

    @Override
    public Optional<Especialidad> buscarPorNombre(String nombre) {
        return especialidadRepository.findByNombreIgnoreCase(nombre);
    }

    @Override
    public Especialidad crearEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @Override
    public void eliminarEspecialidad(Long id) {
        especialidadRepository.deleteById(id);
    }
}
