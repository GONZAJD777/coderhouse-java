package com.coderhouse.ProyectoFinal_PrimeraEntrega.exception;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;

public class CustomException extends Exception {
    private final ErrorType errorType;
    private String CustomExceptionText;

    public CustomException(ErrorType errorType, String pCustomExceptionText) {
        super(errorType.getDescription());
        this.errorType = errorType;
        this.CustomExceptionText =pCustomExceptionText;
    }

    public CustomException(ErrorType errorType) {
        super(errorType.getDescription());
        this.errorType = errorType;
    }



    public ErrorType getErrorType() {
        return errorType;
    }

    public String getCustomExceptionText() {
        return CustomExceptionText;
    }

    public void setCustomExceptionText(String pCustomExceptionText) {
        CustomExceptionText = pCustomExceptionText;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "errorType=" + errorType +
                '}';
    }
}
