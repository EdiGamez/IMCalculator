package com.ievidencia.imcalculator.exceptions;

public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String message) {
        super(message);
    }

    public void printErrorMessage() {

        System.err.println(getMessage());
    }
}