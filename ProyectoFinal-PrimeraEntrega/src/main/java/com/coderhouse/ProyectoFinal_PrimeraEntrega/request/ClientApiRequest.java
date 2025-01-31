package com.coderhouse.ProyectoFinal_PrimeraEntrega.request;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request model for creating or updating a client")
public class ClientApiRequest {

    @Schema(name = "mClientName", description = "Nombre y Apellido del cliente", example = "Juan Pérez")
    private String mClientName;

    @Schema(name = "mClientAddress", description = "Dirección física del cliente", example = "Calle Falsa 123")
    private String mClientAddress;

    @Schema(name = "mClientDocId", description = "CUIL/CUIT del cliente", example = "20123456789")
    private long mClientDocId;

    // Constructor sin argumentos
    public ClientApiRequest() {}

    // Getters y setters

    public String getmClientName() {
        return mClientName;
    }

    public void setmClientName(String mClientName) {
        this.mClientName = mClientName;
    }

    public String getmClientAddress() {
        return mClientAddress;
    }

    public void setmClientAddress(String mClientAddress) {
        this.mClientAddress = mClientAddress;
    }

    public long getmClientDocId() {
        return mClientDocId;
    }

    public void setmClientDocId(long mClientDocId) {
        this.mClientDocId = mClientDocId;
    }
}

