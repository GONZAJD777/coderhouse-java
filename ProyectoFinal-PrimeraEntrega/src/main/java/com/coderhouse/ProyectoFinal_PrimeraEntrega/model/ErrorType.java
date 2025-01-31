package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;


public enum ErrorType {
    PRODUCT_NOT_FOUND("Producto no encontrado con ID %s."),
    PRODUCT_OUT_OF_STOCK("Producto agotado."),
    PRODUCT_CODE_ALREADY_EXIST("El código %s ya existe en otro producto."),
    PRODUCT_CODE_NOT_NULLABLE("El código de producto no puede ser nulo"),

    CLIENT_NOT_FOUND("Cliente no encontrado con ID %s."),
    CLIENT_DOC_ID_ALREADY_EXIST("El número de documento %s ya existe en otro cliente."),
    CLIENT_DOC_NOT_NULLABLE("El documento del cliente no puede ser nulo"),

    TICKET_NOT_FOUND("Ticket %s no encontrado."),
    TICKET_HAS_NO_ITEMS("El ticket no se pudo generar, ningún ítem cumple las condiciones para completar la venta."),

    CART_NOT_FOUND("Carrito no encontrado con ID %s."),
    CART_IS_EMPTY("El carrito no tiene productos."),
    CART_DETAIL_FORMAT_ERROR("El listado de productos no tiene el formato adecuado."),
    CART_ITEM_NOT_FOUND("El producto que estas intentando quitar no esta en el carrito, ID %s."),

    INPUT_ERROR ("Error al ingresar el valor %s, verifica los nombres y valores de los atributos"),
    DATABASE_ISSUES("Error al intentar acceder a la base de datos."),
    DATE_SERVICE_UNAVAILABLE("El servicio para recuperar fechas no responde."),
    SYSTEM_ERROR("Error del sistema.");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    public String getFormattedMessage(Object... params) {
        return String.format(description, params);
    }

    public String getDescription() {
        return description;
    }
}


