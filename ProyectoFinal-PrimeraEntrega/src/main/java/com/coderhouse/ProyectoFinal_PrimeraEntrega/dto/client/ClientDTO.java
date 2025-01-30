package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartReducedDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "ClienteDTO excluye IsActiveFlag, ademas reemplaza Cart por CartReducedDTO para eliminar la referencia cruzada entre Client y Cart.<br>" +
        "Es utilizado por el Client controler donde se requiere toda la inforacion del cliente y solo informacio del contenido del carrito.")
public class ClientDTO {
    @Schema(name="mClientId", description = "ID del Cliente, autoincremental")
    private long mClientId;
    @Schema(name="mClientName", description = "Nombre y Apellido del cliente")
    private String mClientName;
    @Schema(name="mClientAddress", description = "Direccion fisica del cliente")
    private String mClientAddress;
    @Schema(name="mClientDocId", description = "CUIL/CUIT del cliente")
    private long mClientDocId;
    @Schema(name="mClientCreationDate", description = "Fecha y hora de alta del cliente")
    private LocalDateTime mClientCreationDate;
    @Schema(name="mClientCart", description = "Carrito del cliente")
    private CartReducedDTO mClientCart;

    public long getmClientId() {
        return mClientId;
    }

    public void setmClientId(long pClientId) {
        mClientId = pClientId;
    }

    public String getmClientName() {
        return mClientName;
    }

    public void setmClientName(String pClientName) {
        mClientName = pClientName;
    }

    public String getmClientAddress() {
        return mClientAddress;
    }

    public void setmClientAddress(String pClientAddress) {
        mClientAddress = pClientAddress;
    }

    public long getmClientDocId() {
        return mClientDocId;
    }

    public void setmClientDocId(long pClientDocId) {
        mClientDocId = pClientDocId;
    }

    public LocalDateTime getmClientCreationDate() {
        return mClientCreationDate;
    }

    public void setmClientCreationDate(LocalDateTime pClientCreationDate) {
        mClientCreationDate = pClientCreationDate;
    }

    public CartReducedDTO getmClientCart() {
        return mClientCart;
    }

    public void setmClientCart(CartReducedDTO pClientCart) {
        mClientCart = pClientCart;
    }
// Getters y Setters
}
