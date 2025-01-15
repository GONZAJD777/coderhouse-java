package com.coderhouse.ProyectoFinal_SegundaEntrega.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Client mCartClient;

    @OneToMany(mappedBy = "mCartDetailCart", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartDetail> mCartDetailList=new ArrayList<>();

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

    public Client getmCartClient() {
        return mCartClient;
    }

    public void setmCartClient(Client pCartClient) {
        mCartClient = pCartClient;
    }

    public List<CartDetail> getmCartDetailList() {
        return mCartDetailList;
    }

    public void setmCartDetailList(List<CartDetail> pCartDetailList) {
        mCartDetailList = pCartDetailList;
    }

    public LocalDateTime getmCartCreationDate() {
        return mCartCreationDate;
    }

    public void setmCartCreationDate(LocalDateTime pCartCreationDate) {
        mCartCreationDate = pCartCreationDate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "mCartId=" + mCartId +
                ", mCartClient=" + mCartClient +
                ", mCartDetailList=" + mCartDetailList +
                ", mCartCreationDate=" + mCartCreationDate +
                '}';
    }
}
