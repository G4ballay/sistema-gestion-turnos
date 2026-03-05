package com.gonzalo.turnos.exeption;

public class TurnoNoExisteException extends RuntimeException {
    public TurnoNoExisteException(String mensaje) {
        super(mensaje);
    }
}
