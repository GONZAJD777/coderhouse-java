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
        super();
    }

    public CartDetail(Product mCartDetailProduct, int mCartDetailItemQuantity) {
        this.mCartDetailProduct = mCartDetailProduct;
        this.mCartDetailItemQuantity = mCartDetailItemQuantity;
    }

    //-*****************************************************************-//

    public Long getCartDetailId() {
        return mCartDetailId;
    }

    public void setCartDetailId(Long pCartDetailId) {
        mCartDetailId = pCartDetailId;
    }
    public Cart getCartDetailCart() {
        return mCartDetailCart;
    }

    public void setCartDetailCart(Cart pCartDetailCart) {
        mCartDetailCart = pCartDetailCart;
    }
    public Product getCartDetailProduct() {
        return mCartDetailProduct;
    }

    public void setCartDetailProduct(Product pCartDetailProduct) {
        mCartDetailProduct = pCartDetailProduct;
    }

    public int getCartDetailItemQuantity() {
        return mCartDetailItemQuantity;
    }

    public void setCartDetailItemQuantity(int pCartDetailItemQuantity) {
        mCartDetailItemQuantity = pCartDetailItemQuantity;
    }

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

