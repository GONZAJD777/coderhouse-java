package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product.ProductDTO;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CartDetailDTO solo incluye el ProductoDTO y la cantidad del Producto, eliminando la informacion de referencias a carrito y producto.")

public class CartDetailDTO {
    @Schema(name="mCartDetailItemProduct", description = "Producto que constituye el detalle del carrito")
    private ProductDTO mCartDetailItemProduct;
    @Schema(name="mCartDetailItemQuantity", description = "Cantidad de unidades de Producto que constituye el detalle del carrito")
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
