package com.gonzalo.turnos.service;

import com.gonzalo.turnos.entity.Especialidad;
import com.gonzalo.turnos.entity.Profesional;
import com.gonzalo.turnos.exeption.*;
import com.gonzalo.turnos.repository.EspecialidadRepository;
import com.gonzalo.turnos.repository.ProfesionalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService implements IProfesionalService{

    private final ProfesionalRepository profesionalRepository;
    private final EspecialidadRepository especialidadRepository;

    public ProfesionalService(ProfesionalRepository profesionalRepository,
                              EspecialidadRepository especialidadRepository) {
        this.profesionalRepository = profesionalRepository;
        this.especialidadRepository = especialidadRepository;
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
        if(profesional.getNombre() == null || profesional.getNombre().isBlank()){
            throw new NombreObligatorioException("El nombre es obligatorio");
        }

        if(profesional.getApellido() == null || profesional.getApellido().isBlank()) {
            throw new ApellidoObligatorioException("El apellido es obligatorio");
        }

        if(profesional.getEmail() == null || profesional.getEmail().isBlank()){
            throw new EmailObligatorioException("El email es obligatorio");
        }

        if(profesionalRepository.findByEmail(profesional.getEmail()).isPresent()){
            throw new EmailYaRegistradoException("Ya existe un profesional con ese email");
        }

        if(profesional.getEspecialidad() == null || profesional.getEspecialidad().getId() == null){
            throw new EspecialidadObligatoriaException("El profesional debe tener una especialidad");
        }

        Long especialidadId = profesional.getEspecialidad().getId();

        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EspecialidadNoEncontradaException("Especialidad no encontrada"));

        profesional.setEspecialidad(especialidad);

        profesional.setCreatedAt(LocalDateTime.now());

        return profesionalRepository.save(profesional);
    }

    @Override
    public void eliminarProfesional(Long id) {

        if(!profesionalRepository.existsById(id)){
            throw new ProfesionalNoEncontradoException("Profesional no encontrado");
        }

        profesionalRepository.deleteById(id);
    }
}
