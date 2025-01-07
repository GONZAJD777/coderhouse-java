package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto;

import java.time.LocalDateTime;

public class ClientReducedDTO {

    private long mClientId;
    private String mClientName;
    private String mClientAddress;
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
