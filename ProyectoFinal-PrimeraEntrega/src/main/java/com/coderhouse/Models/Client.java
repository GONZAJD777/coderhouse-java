package com.coderhouse.Models;

import java.time.LocalDateTime;

public class Client {

    private Long mClientId;
    private String mClientName;
    private String mClientAddres;
    private Long mClientDocID;
    private LocalDateTime mClientCreationDate;

    public Long getClientId() {
        return mClientId;
    }

    public void setClientId(Long pClientId) {
        this.mClientId = pClientId;
    }

    public String getClientName() {
        return mClientName;
    }

    public void setClientName(String pClientName) {
        this.mClientName = pClientName;
    }

    public String getClientAddres() {
        return mClientAddres;
    }

    public void setClientAddres(String pClientAddres) {
        this.mClientAddres = pClientAddres;
    }

    public Long getClientDocID() {
        return mClientDocID;
    }

    public void setClientDocID(Long pClientDocID) {
        this.mClientDocID = pClientDocID;
    }

    public LocalDateTime getClientCreationDate() {
        return mClientCreationDate;
    }

    public void setClientCreationDate(LocalDateTime pClientCreationDate) {
        this.mClientCreationDate = pClientCreationDate;
    }


    @Override
    public String toString() {
        return "Client{" +
                "mClientId=" + mClientId +
                ", mClientName='" + mClientName + '\'' +
                ", mClientAddres='" + mClientAddres + '\'' +
                ", mClientDocID=" + mClientDocID +
                ", mClientCreationDate=" + mClientCreationDate +
                '}';
    }



}
