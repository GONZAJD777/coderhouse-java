package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;


public enum ErrorType {
    PRODUCT_NOT_FOUND("Producto no encontrado."),
    PRODUCT_OUT_OF_STOCK("Producto agotado."),
    PRODUCT_CODE_ALREADY_EXIST("El codigo ya existe en otro producto."),
    PRODUCT_CODE_NOT_NULLABLE("El codigo de producto no puede ser nulo"),

    CLIENT_NOT_FOUND("Cliente no encontrado."),
    CLIENT_DOC_ID_ALREADY_EXIST("El numero de documento ya existe en otro cliente."),

    TICKET_NOT_FOUND("Ticket no encontrado."),
    TICKET_HAS_NO_ITEMS("El ticket no se pudo generar, ningun item cumple las condiciones para completar la venta."),

    CART_NOT_FOUND ("Carrito no encontrado."),
    CART_IS_EMPTY ("El carrito no tiene productos."),

    DATABASE_ISSUES("Error al intentar acceder a la base de datos."),
    DATE_SERVICE_UNAVAILABLE("El servicio para recuperar fechas no responde."),
    SYSTEM_ERROR("Error del sistema.");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

