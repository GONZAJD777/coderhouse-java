package com.coderhouse.ProyectoFinal_PrimeraEntrega.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Respuesta de API")
public class ApiResponse<T> {
    @Schema(name="success", description = "Estado de la operacion ejecutada")
    private boolean success;
    @Schema(name="message", description = "Respuesta decribiendo el resultado de la operacion")
    private String message;
    @Schema(name="data", description = "Objeto con los datos resultado de la operacion (payload)")
    private T data;
    @Schema(name="errors", description = "Listado de los errores ocurridos durante la operacion")
    private List<String> errors;

    public ApiResponse() {
        this.errors = new ArrayList<>();
    }

    public ApiResponse(boolean success, String message, T data, List<String> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors != null ? errors : new ArrayList<>();
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}



