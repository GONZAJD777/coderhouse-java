package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ClienteDTO excluye mIsActiveFlag, mClientCreationDate y mClientCart.<br> " +
        "Contiene solo la informacion basica del cliente para ser mostrada en el encabezado de los Tickets y el en la respuestas de Cart Controller <br>" +
        "donde informacion del carrito completo e informacion basica del cliente es requerida.")

public class ClientReducedDTO {

    @Schema(name="mClientId", description = "ID del Cliente, autoincremental")
    private long mClientId;
    @Schema(name="mClientName", description = "Nombre y Apellido del cliente")
    private String mClientName;
    @Schema(name="mClientAddress", description = "Direccion fisica del cliente")
    private String mClientAddress;
    @Schema(name="mClientDocId", description = "CUIL/CUIT del cliente")
    private long mClientDocId;

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
}
