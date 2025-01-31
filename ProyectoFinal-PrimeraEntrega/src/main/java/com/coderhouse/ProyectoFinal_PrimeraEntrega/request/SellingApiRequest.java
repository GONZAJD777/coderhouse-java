package com.coderhouse.ProyectoFinal_PrimeraEntrega.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Request model for creating a sale")
public class SellingApiRequest {

    @Schema(description = "ID del cliente", example = "25")
    private Long cliente;

    @Schema(description = "Detalle del carrito", example = "[{\"producto\": 1, \"cantidad\": 6}, {\"producto\": 2, \"cantidad\": 2}, {\"producto\": 3, \"cantidad\": 1}]")
    private List<ItemDetalleCarrito> detalleCarrito;

    public SellingApiRequest() {}

    @JsonCreator
    public SellingApiRequest(@JsonProperty("cliente") Long cliente,
                             @JsonProperty("detalleCarrito") List<ItemDetalleCarrito> detalleCarrito) {
        this.cliente = cliente;
        this.detalleCarrito = detalleCarrito;
    }

    // Getters y setters
    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public List<ItemDetalleCarrito> getDetalleCarrito() {
        return detalleCarrito;
    }

    public void setDetalleCarrito(List<ItemDetalleCarrito> detalleCarrito) {
        this.detalleCarrito = detalleCarrito;
    }

    @Schema(description = "Detalle del carrito")
    public static class ItemDetalleCarrito {

        @Schema(description = "ID del producto", example = "155")
        private Long producto;

        @Schema(description = "Cantidad de producto", example = "6")
        private Integer cantidad;

        public ItemDetalleCarrito() {}

        @JsonCreator
        public ItemDetalleCarrito(@JsonProperty("producto") Long producto,
                                  @JsonProperty("cantidad") Integer cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }

        // Getters y setters
        public Long getProducto() {
            return producto;
        }

        public void setProducto(Long producto) {
            this.producto = producto;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
    }
}
