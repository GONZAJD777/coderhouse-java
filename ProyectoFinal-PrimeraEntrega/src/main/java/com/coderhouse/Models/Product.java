package com.coderhouse.Models;

import java.time.LocalDateTime;

public class Product {

    private Long mProductId;
    private String mProductName;
    private String mProductDescription;
    private String mProductCategory;
    private String mProductCode;
    private String mProductPrice;
    private String mProductTax;
    private LocalDateTime mProductCreationDate;



    public Long getProductId() {
        return mProductId;
    }

    public void setProductId(Long pProductId) {
        mProductId = pProductId;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String pProductName) {
        mProductName = pProductName;
    }

    public String getProductDescription() {
        return mProductDescription;
    }

    public void setProductDescription(String pProductDescription) {
        mProductDescription = pProductDescription;
    }

    public String getProductCategory() {
        return mProductCategory;
    }

    public void setProductCategory(String pProductCategory) {
        mProductCategory = pProductCategory;
    }

    public String getProductCode() {
        return mProductCode;
    }

    public void setProductCode(String pProductCode) {
        mProductCode = pProductCode;
    }

    public String getProductPrice() {
        return mProductPrice;
    }

    public void setProductPrice(String pProductPrice) {
        mProductPrice = pProductPrice;
    }

    public String getProductTax() {
        return mProductTax;
    }

    public void setProductTax(String pProductTax) {
        mProductTax = pProductTax;
    }

    public LocalDateTime getProductCreationDate() {
        return mProductCreationDate;
    }

    public void setProductCreationDate(LocalDateTime pProductCreationDate) {
        mProductCreationDate = pProductCreationDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "mProductId=" + mProductId +
                ", mProductName='" + mProductName + '\'' +
                ", mProductDescription='" + mProductDescription + '\'' +
                ", mProductCategory='" + mProductCategory + '\'' +
                ", mProductCode='" + mProductCode + '\'' +
                ", mProductPrice='" + mProductPrice + '\'' +
                ", mProductTax='" + mProductTax + '\'' +
                ", mProductCreationDate=" + mProductCreationDate +
                '}';
    }
}
