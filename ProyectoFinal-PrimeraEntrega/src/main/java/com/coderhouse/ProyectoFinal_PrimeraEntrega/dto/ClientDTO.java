package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto;

import java.time.LocalDateTime;

public class ClientDTO {
    private long mClientId;
    private String mClientName;
    private String mClientAddress;
    private long mClientDocId;
    private LocalDateTime mClientCreationDate;
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
