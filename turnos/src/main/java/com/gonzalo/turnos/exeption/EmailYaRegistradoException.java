package com.gonzalo.turnos.exeption;

public class EmailYaRegistradoException extends RuntimeException{
    public EmailYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}
