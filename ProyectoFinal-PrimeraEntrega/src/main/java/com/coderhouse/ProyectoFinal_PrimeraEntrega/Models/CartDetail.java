package com.coderhouse.ProyectoFinal_PrimeraEntrega.Models;


import jakarta.persistence.*;

@Entity
@Table(name="carts_detail")
public class CartDetail {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="cart_detail_id")
    private Long mCartDetailId;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_detail_product_id", referencedColumnName = "product_id")
    private Product mCartDetailProduct;

    @Column(name="cart_detail_item_quantity")
    private int mCartDetailItemQuantity;

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
                ", mCartDetailProduct=" + mCartDetailProduct +
                ", mCartDetailItemQuantity=" + mCartDetailItemQuantity +
                '}';
    }
}

