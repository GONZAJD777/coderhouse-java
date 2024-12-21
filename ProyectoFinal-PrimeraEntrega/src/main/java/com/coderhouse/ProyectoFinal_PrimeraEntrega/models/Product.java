package com.coderhouse.ProyectoFinal_PrimeraEntrega.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="product_id")
    private Long mProductId;
    @Column(name="product_name")
    private String mProductName;
    @Column(name="product_description")
    private String mProductDescription;
    @Column(name="product_category")
    private String mProductCategory;
    @Column(name="product_code", unique = true, nullable = false)
    private String mProductCode;
    @Column(name="product_stock")
    private Integer mProductStock;
    @Column(name="product_price")
    private Float mProductPrice;
    @Column(name="product_tax_percent")
    private Float mProductTaxPercent;
    @Column(name="product_creation_date")
    private LocalDateTime mProductCreationDate;

    public Product() {
        super();
    }

    public Product(String pProductName, String pProductDescription, String pProductCategory, String pProductCode, Float pProductPrice, Float pProductTaxPercent, Integer pProductStock) {
        this.mProductName = pProductName;
        this.mProductDescription = pProductDescription;
        this.mProductCategory = pProductCategory;
        this.mProductCode = pProductCode;
        this.mProductPrice = pProductPrice;
        this.mProductTaxPercent = pProductTaxPercent;
        this.mProductCreationDate=LocalDateTime.now();
        this.mProductStock=pProductStock;
        this.mProductCreationDate= LocalDateTime.now();
    }

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

    @Override
    public String toString() {
        return "Product{" +
                "mProductId=" + mProductId +
                ", mProductName='" + mProductName + '\'' +
                ", mProductDescription='" + mProductDescription + '\'' +
                ", mProductCategory='" + mProductCategory + '\'' +
                ", mProductCode='" + mProductCode + '\'' +
                ", mProductStock=" + mProductStock +
                ", mProductPrice='" + mProductPrice + '\'' +
                ", mProductTaxPercent='" + mProductTaxPercent + '\'' +
                ", mProductCreationDate=" + mProductCreationDate +
                '}';
    }
}
