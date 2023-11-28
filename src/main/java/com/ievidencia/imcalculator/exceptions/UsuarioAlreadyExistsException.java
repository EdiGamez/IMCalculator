package com.ievidencia.imcalculator.exceptions;

public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String message) {
        super(message);
    }

    public void printErrorMessage() {
        // Use this method to print the error message to the console
        System.err.println(getMessage());
    }
}