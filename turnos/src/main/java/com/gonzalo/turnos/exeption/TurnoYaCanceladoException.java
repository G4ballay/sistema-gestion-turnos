package com.gonzalo.turnos.exeption;

public class TurnoYaCanceladoException extends RuntimeException {
    public TurnoYaCanceladoException(String mensaje) {
        super(mensaje);
    }
}
