package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product;


import java.time.LocalDateTime;

public class ProductDTO {
    private Long mProductId;
    private String mProductName;
    private String mProductDescription;
    private String mProductCategory;
    private String mProductCode;
    private Integer mProductStock;
    private Float mProductPrice;
    private Float mProductTaxPercent;
    private LocalDateTime mProductCreationDate;

    public Long getmProductId() {
        return mProductId;
    }

    public void setmProductId(Long pProductId) {
        mProductId = pProductId;
    }

    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String pProductName) {
        mProductName = pProductName;
    }

    public String getmProductDescription() {
        return mProductDescription;
    }

    public void setmProductDescription(String pProductDescription) {
        mProductDescription = pProductDescription;
    }

    public String getmProductCategory() {
        return mProductCategory;
    }

    public void setmProductCategory(String pProductCategory) {
        mProductCategory = pProductCategory;
    }

    public String getmProductCode() {
        return mProductCode;
    }

    public void setmProductCode(String pProductCode) {
        mProductCode = pProductCode;
    }

    public Integer getmProductStock() {
        return mProductStock;
    }

    public void setmProductStock(Integer pProductStock) {
        mProductStock = pProductStock;
    }

    public Float getmProductPrice() {
        return mProductPrice;
    }

    public void setmProductPrice(Float pProductPrice) {
        mProductPrice = pProductPrice;
    }

    public Float getmProductTaxPercent() {
        return mProductTaxPercent;
    }

    public void setmProductTaxPercent(Float pProductTaxPercent) {
        mProductTaxPercent = pProductTaxPercent;
    }

    public LocalDateTime getmProductCreationDate() {
        return mProductCreationDate;
    }

    public void setmProductCreationDate(LocalDateTime pProductCreationDate) {
        mProductCreationDate = pProductCreationDate;
    }
}
