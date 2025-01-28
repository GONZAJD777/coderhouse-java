package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;

public class CartDetailDTO {

        private ProductDTO mCartDetailItemProduct;
        private int mCartDetailItemQuantity;


    public ProductDTO getmCartDetailItemProduct() {
        return mCartDetailItemProduct;
    }

    public void setmCartDetailItemProduct(ProductDTO pCartDetailItemProduct) {
        mCartDetailItemProduct = pCartDetailItemProduct;
    }

    public int getmCartDetailItemQuantity() {
        return mCartDetailItemQuantity;
    }

    public void setmCartDetailItemQuantity(int pCartDetailItemQuantity) {
        mCartDetailItemQuantity = pCartDetailItemQuantity;
    }
}
