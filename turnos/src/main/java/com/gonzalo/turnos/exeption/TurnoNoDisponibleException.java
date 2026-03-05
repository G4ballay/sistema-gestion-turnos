package com.gonzalo.turnos.exeption;

public class TurnoNoDisponibleException extends RuntimeException {
    public TurnoNoDisponibleException(String message) {
        super(message);
    }
}
