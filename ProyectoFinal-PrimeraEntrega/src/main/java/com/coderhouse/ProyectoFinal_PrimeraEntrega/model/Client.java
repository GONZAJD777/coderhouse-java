package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "Cliente")
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

    @Column(name="client_doc_id" ,unique = false, nullable = false) //unico y no nulo
    private Long mClientDocId;

    @Column(name="client_creation_date")
    private LocalDateTime mClientCreationDate;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "client_cart_id", referencedColumnName = "cart_id")
    private Cart mClientCart;

    @Column(name="client_is_active_flag",nullable = false)
    private Boolean mIsActiveFlag;


    public Client() {
        super();
    }

    public Client(String pClientName, String pClientAddress, Long pClientDocId) {
        this.mClientName = pClientName;
        this.mClientAddress = pClientAddress;
        this.mClientDocId = pClientDocId;
        this.mClientCreationDate= LocalDateTime.now();
    }

    public Long getmClientId() {
        return mClientId;
    }

    public void setmClientId(Long pClientId) {
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

    public Long getmClientDocId() {
        return mClientDocId;
    }

    public void setmClientDocId(Long pClientDocId) {
        mClientDocId = pClientDocId;
    }

    public LocalDateTime getmClientCreationDate() {
        return mClientCreationDate;
    }

    public void setmClientCreationDate(LocalDateTime pClientCreationDate) {
        mClientCreationDate = pClientCreationDate;
    }

    public Cart getmClientCart() {
        return mClientCart;
    }

    public void setmClientCart(Cart pClientCart) {
        mClientCart = pClientCart;
    }

    public Boolean getmIsActiveFlag() {
        return mIsActiveFlag;
    }

    public void setmIsActiveFlag(Boolean pIsActiveFlag) {
        mIsActiveFlag = pIsActiveFlag;
    }

    @Override
    public String toString() {
        return "Client{" +
                "mClientId=" + mClientId +
                ", mClientName='" + mClientName + '\'' +
                ", mClientAddress='" + mClientAddress + '\'' +
                ", mClientDocId=" + mClientDocId +
                ", mClientCreationDate=" + mClientCreationDate +
                ", mClientCart=" + mClientCart +
                '}';
    }


}
