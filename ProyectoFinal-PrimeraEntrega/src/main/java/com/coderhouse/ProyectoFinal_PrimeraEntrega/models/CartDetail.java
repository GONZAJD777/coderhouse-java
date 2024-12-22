package com.coderhouse.ProyectoFinal_PrimeraEntrega.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="carts_detail")
public class CartDetail {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="cart_detail_id")
    private Long mCartDetailId;

    @ManyToOne
    @JoinColumn(name = "cart_detail_cart_id", referencedColumnName = "cart_id")
    private Cart mCartDetailCart;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_detail_product_id", referencedColumnName = "product_id")
    private Product mCartDetailProduct;

    @Column(name="cart_detail_item_quantity")
    private int mCartDetailItemQuantity;

    public CartDetail() {
    }

    public CartDetail(Product mCartDetailProduct, int mCartDetailItemQuantity) {
        this.mCartDetailProduct = mCartDetailProduct;
        this.mCartDetailItemQuantity = mCartDetailItemQuantity;
    }

    //-*****************************************************************-//

    public Long getmCartDetailId() {
        return mCartDetailId;
    }

    public void setmCartDetailId(Long pCartDetailId) {
        mCartDetailId = pCartDetailId;
    }

    public Cart getmCartDetailCart() {
        return mCartDetailCart;
    }

    public void setmCartDetailCart(Cart pCartDetailCart) {
        mCartDetailCart = pCartDetailCart;
    }

    public Product getmCartDetailProduct() {
        return mCartDetailProduct;
    }

    public void setmCartDetailProduct(Product pCartDetailProduct) {
        mCartDetailProduct = pCartDetailProduct;
    }

    public int getmCartDetailItemQuantity() {
        return mCartDetailItemQuantity;
    }

    public void setmCartDetailItemQuantity(int pCartDetailItemQuantity) {
        mCartDetailItemQuantity = pCartDetailItemQuantity;
    }


    //-*****************************************************************-//

    @Override
    public String toString() {
        return "CartDetail{" +
                "mCartDetailId=" + mCartDetailId +
                ", mCartDetailCart=" + mCartDetailCart +
                ", mCartDetailProduct=" + mCartDetailProduct +
                ", mCartDetailItemQuantity=" + mCartDetailItemQuantity +
                '}';
    }
}

