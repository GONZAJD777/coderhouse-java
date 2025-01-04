package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto;

public class CartDetailDTO {
    private long mCartDetailProductId;
    private String mCartDetailProductName;
    private String mCartDetailProductDescription;
    private String mCartDetailProductCategory;
    private double mCartDetailProductPrice;
    private int mCartDetailItemQuantity;

    public long getmCartDetailProductId() {
        return mCartDetailProductId;
    }

    public void setmCartDetailProductId(long pCartDetailProductId) {
        mCartDetailProductId = pCartDetailProductId;
    }

    public String getmCartDetailProductName() {
        return mCartDetailProductName;
    }

    public void setmCartDetailProductName(String pCartDetailProductName) {
        mCartDetailProductName = pCartDetailProductName;
    }

    public String getmCartDetailProductDescription() {
        return mCartDetailProductDescription;
    }

    public void setmCartDetailProductDescription(String pCartDetailProductDescription) {
        mCartDetailProductDescription = pCartDetailProductDescription;
    }

    public String getmCartDetailProductCategory() {
        return mCartDetailProductCategory;
    }

    public void setmCartDetailProductCategory(String pCartDetailProductCategory) {
        mCartDetailProductCategory = pCartDetailProductCategory;
    }

    public double getmCartDetailProductPrice() {
        return mCartDetailProductPrice;
    }

    public void setmCartDetailProductPrice(double pCartDetailProductPrice) {
        mCartDetailProductPrice = pCartDetailProductPrice;
    }

    public int getmCartDetailItemQuantity() {
        return mCartDetailItemQuantity;
    }

    public void setmCartDetailItemQuantity(int pCartDetailItemQuantity) {
        mCartDetailItemQuantity = pCartDetailItemQuantity;
    }
// Getters y Setters
}
