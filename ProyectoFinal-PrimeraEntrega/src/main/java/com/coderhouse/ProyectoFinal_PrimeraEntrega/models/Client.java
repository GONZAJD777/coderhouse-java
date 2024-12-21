package com.coderhouse.ProyectoFinal_PrimeraEntrega.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mClientId")
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
    private Long mClientDocId;

    @Column(name="client_creation_date")
    private LocalDateTime mClientCreationDate;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "client_cart_id", referencedColumnName = "cart_id")
    //@JsonIgnoreProperties("mCartClient")
    private Cart mClientCart;

    public Client() {
        super();
    }

    public Client(String pClientName, String pClientAddress, Long pClientDocId) {
        this.mClientName = pClientName;
        this.mClientAddress = pClientAddress;
        this.mClientDocId = pClientDocId;
        //this.mClientCreationDate= LocalDateTime.now();
    }

    public Long getmClientId() {
        return mClientId;
    }

    public void setmClientId(Long pClientId) {
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

    public Long getClientDocId() {
        return mClientDocId;
    }

    public void setClientDocId(Long pClientDocId) {
        this.mClientDocId = pClientDocId;
    }

    public LocalDateTime getClientCreationDate() {
        return mClientCreationDate;
    }

    public void setClientCreationDate(LocalDateTime pClientCreationDate) { this.mClientCreationDate=pClientCreationDate; }

    public Cart getClientCart() { return mClientCart; }

    public void setClientCart(Cart pClientCart) { mClientCart = pClientCart; }

    @Override
    public String toString() {
        return "Client{" +
                "mClientId=" + mClientId +
                ", mClientName='" + mClientName + '\'' +
                ", mClientAddress='" + mClientAddress + '\'' +
                ", mClientDocID=" + mClientDocId +
                ", mClientCreationDate=" + mClientCreationDate +
                ", mClientCart=" + mClientCart +
                '}';
    }


}
