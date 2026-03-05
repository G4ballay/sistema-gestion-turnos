package com.gonzalo.turnos.exeption;

public class NoPermisoTurnoException extends RuntimeException {
    public NoPermisoTurnoException(String mensaje) {
        super(mensaje);
    }
}
