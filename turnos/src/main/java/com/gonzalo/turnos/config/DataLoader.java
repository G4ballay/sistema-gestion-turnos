package com.gonzalo.turnos.config;

import com.gonzalo.turnos.entity.*;
import com.gonzalo.turnos.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final EspecialidadRepository especialidadRepository;
    private final ProfesionalRepository profesionalRepository;
    private final TurnoRepository turnoRepository;

    public DataLoader(
            EspecialidadRepository especialidadRepository,
            ProfesionalRepository profesionalRepository,
            TurnoRepository turnoRepository) {

        this.especialidadRepository = especialidadRepository;
        this.profesionalRepository = profesionalRepository;
        this.turnoRepository = turnoRepository;
    }

    @Override
    @ConditionalOnProperty(name="app.dataloader.enabled", havingValue="true")
    public void run(String... args) {

        if(especialidadRepository.count() > 0){
            return;
        }

        System.out.println("Cargando datos de prueba...");

        // ESPECIALIDADES
        Especialidad cardio = new Especialidad();
        cardio.setNombre("Cardiologia");

        Especialidad derma = new Especialidad();
        derma.setNombre("Dermatologia");

        especialidadRepository.save(cardio);
        especialidadRepository.save(derma);

        // PROFESIONALES
        Profesional p1 = new Profesional();
        p1.setNombre("Juan");
        p1.setApellido("Perez");
        p1.setEmail("juan@hospital.com");
        p1.setEspecialidad(cardio);

        Profesional p2 = new Profesional();
        p2.setNombre("Laura");
        p2.setApellido("Gomez");
        p2.setEmail("laura@hospital.com");
        p2.setEspecialidad(derma);

        profesionalRepository.save(p1);
        profesionalRepository.save(p2);

        // GENERAR TURNOS
        for (Profesional profesional : profesionalRepository.findAll()) {

            for (int i = 1; i <= 7; i++) {

                LocalDate fecha = LocalDate.now().plusDays(i);

                for (LocalTime hora = LocalTime.of(9,0);
                     hora.isBefore(LocalTime.of(17,0));
                     hora = hora.plusMinutes(30)) {

                    Turno turno = new Turno();
                    turno.setProfesional(profesional);
                    turno.setFechaHora(LocalDateTime.of(fecha, hora));

                    turnoRepository.save(turno);
                }
            }
        }
    }
}
