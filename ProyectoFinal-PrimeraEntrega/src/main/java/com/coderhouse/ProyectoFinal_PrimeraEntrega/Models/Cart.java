package com.coderhouse.ProyectoFinal_PrimeraEntrega.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="carts")
public class Cart {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="cart_id")
    private Long mCartID;

    @OneToOne(mappedBy = "mClientCart", fetch = FetchType.EAGER)
    private Client mCartClient;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="cart_detail_cart_id")
    private List<CartDetail> mCartDetailList;

    @Column(name="cart_creation_date")
    private LocalDateTime mCartCreationDate;

    public Cart(Client pCartClient, List<CartDetail> pCartDetailList) {
        this.mCartClient = pCartClient;
        this.mCartDetailList = pCartDetailList;
        this.mCartCreationDate = LocalDateTime.now();
    }

    public Long getCartID() {
        return mCartID;
    }

    public void setCartID(Long pCartID) {
        mCartID = pCartID;
    }

    public Client getCartClient() {
        return mCartClient;
    }

    public void setCartClient(Client pCartClient) {
        mCartClient = pCartClient;
    }

    public List<CartDetail> getCartDetailList() {
        return mCartDetailList;
    }

    public void setCartDetailList(List<CartDetail> pCartDetailList) {
        mCartDetailList = pCartDetailList;
    }

    public LocalDateTime getCartCreationDate() {
        return mCartCreationDate;
    }
    public void setCartCreationDate(LocalDateTime pCartCreationDate) {
        mCartCreationDate = pCartCreationDate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "mCartID=" + mCartID +
                ", mCartClientId=" + mCartClient +
                ", mCartDetailList=" + mCartDetailList +
                ", mCartCreationDate=" + mCartCreationDate +
                '}';
    }
}
