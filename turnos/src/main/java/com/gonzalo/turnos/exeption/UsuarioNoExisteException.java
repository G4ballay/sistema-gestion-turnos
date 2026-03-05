package com.gonzalo.turnos.exeption;

public class UsuarioNoExisteException extends RuntimeException {
    public UsuarioNoExisteException(String message) {
        super(message);
    }
}
