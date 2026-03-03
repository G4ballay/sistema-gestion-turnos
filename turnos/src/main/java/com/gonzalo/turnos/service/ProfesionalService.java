package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.Profesional;
import com.gonzalo.turnos.repository.ProfesionalRepository;

import java.util.List;
import java.util.Optional;

public class ProfesionalService implements IProfesionalService{

    private final ProfesionalRepository profesionalRepository;

    public ProfesionalService(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }

    @Override
    public List<Profesional> listarProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public List<Profesional> buscarPorEspecialidad(long especialidadId) {
        return profesionalRepository.findByEspecialidadId(especialidadId);
    }

    @Override
    public Optional<Profesional> buscarPorId(Long id) {
        return profesionalRepository.findById(id);
    }

    @Override
    public List<Profesional> buscarPorApellido(String apellido) {
        return profesionalRepository.findByApellidoStartingWithIgnoreCase(apellido);
    }

    @Override
    public List<Profesional> buscarPorNombre(String nombre) {
        return profesionalRepository.findByNombreStartingWithIgnoreCase(nombre);
    }

    @Override
    public Profesional crearProfesional(Profesional profesional) {
        return profesionalRepository.save(profesional);
    }

    @Override
    public void eliminarProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }
}
