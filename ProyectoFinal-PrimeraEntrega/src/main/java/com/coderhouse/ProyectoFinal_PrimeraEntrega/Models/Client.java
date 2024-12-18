package com.coderhouse.ProyectoFinal_PrimeraEntrega.Models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="clients")
public class Client {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="client_id")
    private Long mClientId;

    @Column(name="client_name")
    private String mClientName;

    @Column(name="client_address")
    private String mClientAddress;

    @Column(name="client_doc_id" ,unique = true, nullable = false) //unico y no nulo
    private Long mClientDocID;

    @Column(name="client_creation_date")
    private LocalDateTime mClientCreationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_cart_id", referencedColumnName = "cart_id")
    private Cart mClientCart;

    public Client(String pClientName, String pClientAddress, Long pClientDocID) {
        this.mClientName = pClientName;
        this.mClientAddress = pClientAddress;
        this.mClientDocID = pClientDocID;
        this.mClientCreationDate= LocalDateTime.now();
    }

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

    public String getClientAddress() {
        return mClientAddress;
    }

    public void setClientAddress(String pClientAddress) {
        this.mClientAddress = pClientAddress;
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

    public void setClientCreationDate(LocalDateTime pClientCreationDate) { this.mClientCreationDate = pClientCreationDate; }

    public Cart getClientCart() { return mClientCart; }

    public void setClientCart(Cart pClientCart) { mClientCart = pClientCart; }

    @Override
    public String toString() {
        return "Client{" +
                "mClientId=" + mClientId +
                ", mClientName='" + mClientName + '\'' +
                ", mClientAddress='" + mClientAddress + '\'' +
                ", mClientDocID=" + mClientDocID +
                ", mClientCreationDate=" + mClientCreationDate +
                ", mClientCart=" + mClientCart +
                '}';
    }


}
