package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "CartReducedDTO descarta la informacion del cliente, a modo de eliminar el problema de la referencia cruzada.<br>" +
        "Similar al CartDTO pero para ser usado por Client Controller, ya que desde alli solo importa ver el contenido del carrito.")
public class CartReducedDTO {
    @Schema(name="mCartId", description = "ID del Carrito, autoincremental")
    private long mCartId;
    @Schema(name="mCartCreationDate", description = "Fecha de creacion del carrito, ")
    private LocalDateTime mCartCreationDate;
    @Schema(name="mCartDetailList", description = "Listado de items que constituyen el detalle del carrito")
    private List<CartDetailDTO> mCartDetailList;

    public long getmCartId() {
        return mCartId;
    }

    public void setmCartId(long pCartId) {
        mCartId = pCartId;
    }

    public LocalDateTime getmCartCreationDate() {
        return mCartCreationDate;
    }

    public void setmCartCreationDate(LocalDateTime pCartCreationDate) {
        mCartCreationDate = pCartCreationDate;
    }

    public List<CartDetailDTO> getmCartDetailList() {
        return mCartDetailList;
    }

    public void setmCartDetailList(List<CartDetailDTO> pCartDetailList) {
        mCartDetailList = pCartDetailList;
    }
}
