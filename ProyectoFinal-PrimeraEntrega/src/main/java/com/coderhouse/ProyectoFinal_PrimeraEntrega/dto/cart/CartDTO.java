package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client.ClientReducedDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
@Schema(description = "CartDTO reemplaza el objeto de la clase Client, por un objeto de la clase ClientReducedDTO el cual no incluye informacion del carrito, eliminando la referencia cruzada.<br>" +
        "Ademas reemplaza los objetos CartDetail por CartDetailDTO los cuales no muestran informacion de innecesaria como referencias.<br>" +
        "Es utilizado para listar los carritos en las respuestas del  Cart Controller, donde se hace foco en los carritos y solo es necesario mostrar informacion basica del cliente.")
public class CartDTO {
    @Schema(name="mCartId", description = "ID del Carrito, autoincremental")
    private long mCartId;
    @Schema(name="mCartCreationDate", description = "Fecha de creacion del carrito, ")
    private LocalDateTime mCartCreationDate;
    @Schema(name="mCartClient", description = "Cliente al que pertenece el carrito")
    private ClientReducedDTO mCartClient;
    @Schema(name="mCartDetailList", description = "Listado de items que constituyen el detalle del carrito")
    private List<CartDetailDTO> mCartDetailList;


    public LocalDateTime getmCartCreationDate() {
        return mCartCreationDate;
    }

    public void setmCartCreationDate(LocalDateTime pCartCreationDate) {
        mCartCreationDate = pCartCreationDate;
    }

    public ClientReducedDTO getmCartClient() {
        return mCartClient;
    }

    public void setmCartClient(ClientReducedDTO pCartClient) {
        mCartClient = pCartClient;
    }

    public List<CartDetailDTO> getmCartDetailList() {
        return mCartDetailList;
    }

    public void setmCartDetailList(List<CartDetailDTO> pCartDetailList) {
        mCartDetailList = pCartDetailList;
    }

    public long getmCartId() {
        return mCartId;
    }

    public void setmCartId(long pCartId) {
        mCartId = pCartId;
    }

    // Getters y Setters
}

