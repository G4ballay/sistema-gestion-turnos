package com.gonzalo.turnos.exeption;

public class EmailObligatorioException extends RuntimeException {
    public EmailObligatorioException(String message) {
        super(message);
    }
}
