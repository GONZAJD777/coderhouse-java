package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "CartDetail")
@Table(name="carts_detail")
public class CartDetail {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Schema(name="mCartDetailId", description = "ID del item detalle de Carrito, autoincremental")
    @Column(name="cart_detail_id")
    private Long mCartDetailId;

    @Schema(name="mCartDetailCart", description = "Carrito al que pertenece el item detalle de carrito")
    @ManyToOne
    @JoinColumn(name = "cart_detail_cart_id", referencedColumnName = "cart_id")
    private Cart mCartDetailCart;

    @Schema(name="mCartDetailProduct", description = "Producto que constituye el detalle del carrito")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_detail_product_id", referencedColumnName = "product_id")
    private Product mCartDetailProduct;

    @Schema(name="mCartDetailItemQuantity", description = "Cantidad de unidades de Producto que constituye el detalle del carrito")
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

