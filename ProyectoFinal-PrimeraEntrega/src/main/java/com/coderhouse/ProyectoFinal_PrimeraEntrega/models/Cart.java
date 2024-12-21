package com.coderhouse.ProyectoFinal_PrimeraEntrega.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mCartId")
@Table(name="carts")
public class Cart {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="cart_id")
    private Long mCartId;

    @OneToOne(mappedBy = "mClientCart")
    //@JsonIgnoreProperties("mClientCart")
    private Client mCartClient;

    @OneToMany(mappedBy = "mCartDetailCart", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@JoinColumn(name="cart_detail_cart_id")
    private List<CartDetail> mCartDetailList;

    @Column(name="cart_creation_date")
    private LocalDateTime mCartCreationDate;

    public Cart() {
        super();
    }

    public Cart(Client pCartClient, List<CartDetail> pCartDetailList) {
        this.mCartClient = pCartClient;
        this.mCartDetailList = pCartDetailList;
        this.mCartCreationDate = LocalDateTime.now();
    }

    public Long getmCartId() {
        return mCartId;
    }

    public void setmCartId(Long pCartId) {
        mCartId = pCartId;
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
                "mCartId=" + mCartId +
                ", mCartClientId=" + mCartClient +
                ", mCartDetailList=" + mCartDetailList +
                ", mCartCreationDate=" + mCartCreationDate +
                '}';
    }
}
