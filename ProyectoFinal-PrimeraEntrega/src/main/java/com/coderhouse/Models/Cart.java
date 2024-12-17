package com.coderhouse.Models;

import java.time.LocalDateTime;
import java.util.List;

public class Cart {

    private Long mCartID;
    private Long mCartClient;
    private List<Product> mCartProductList;
    private LocalDateTime mCartCreationDate;

    public Long getCartID() {
        return mCartID;
    }

    public void setCartID(Long pCartID) {
        mCartID = pCartID;
    }

    public Long getCartClient() {
        return mCartClient;
    }

    public void setCartClient(Long pCartClient) {
        mCartClient = pCartClient;
    }

    public List<Product> getCartProductList() {
        return mCartProductList;
    }

    public void setCartProductList(List<Product> pCartProductList) {
        mCartProductList = pCartProductList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "mCartID=" + mCartID +
                ", mCartClient=" + mCartClient +
                ", mCartProductList=" + mCartProductList +
                '}';
    }
}
