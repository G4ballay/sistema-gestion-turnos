package com.gonzalo.turnos.dtos;
import com.gonzalo.turnos.entity.EstadoTurno;
import java.time.LocalDateTime;

public class TurnosDto {
    private Long id;
    private LocalDateTime fechaHora;
    private EstadoTurno estado;
    private Long profesionalId;
    private String profesionalNombre;
    private Long usuarioId;
    private String usuarioNombre;
}
