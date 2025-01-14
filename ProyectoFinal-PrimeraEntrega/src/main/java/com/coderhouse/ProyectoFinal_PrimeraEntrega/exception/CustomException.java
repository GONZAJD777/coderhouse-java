package com.coderhouse.ProyectoFinal_PrimeraEntrega.exception;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;

public class CustomException extends Exception {
    private final ErrorType errorType;

    public CustomException(ErrorType errorType) {
        super(errorType.getDescription());
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "errorType=" + errorType +
                '}';
    }
}
