package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(description = "Cart")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "mCartId")
@Table(name="carts")
public class Cart {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Schema(name="mCartId", description = "ID del Carrito, autoincremental")
    @Column(name="cart_id")
    private Long mCartId;

    @Schema(name="mCartClient", description = "Cliente al que pertenece el carrito")
    @OneToOne(mappedBy = "mClientCart")
    private Client mCartClient;

    @Schema(name="mCartDetailList", description = "Listado de items que constituyen el detalle del carrito")
    @OneToMany(mappedBy = "mCartDetailCart", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<CartDetail> mCartDetailList=new ArrayList<>();

    @Schema(name="mCartCreationDate", description = "Fecha de creacion del carrito, ")
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
