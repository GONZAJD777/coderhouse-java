package com.coderhouse.ProyectoFinal_SegundaEntrega.dto.cart;

import com.coderhouse.ProyectoFinal_SegundaEntrega.model.Product;

public class CartDetailDTO {

        private Product mCartDetailItemProduct;
        private int mCartDetailItemQuantity;


    public Product getmCartDetailItemProduct() {
        return mCartDetailItemProduct;
    }

    public void setmCartDetailItemProduct(Product pCartDetailItemProduct) {
        mCartDetailItemProduct = pCartDetailItemProduct;
    }

    public int getmCartDetailItemQuantity() {
        return mCartDetailItemQuantity;
    }

    public void setmCartDetailItemQuantity(int pCartDetailItemQuantity) {
        mCartDetailItemQuantity = pCartDetailItemQuantity;
    }
}
