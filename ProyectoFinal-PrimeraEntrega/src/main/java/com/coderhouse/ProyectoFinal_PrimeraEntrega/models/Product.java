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
    private String mProductPrice;
    @Column(name="product_tax_percent")
    private String mProductTaxPercent;
    @Column(name="product_creation_date")
    private LocalDateTime mProductCreationDate;

    public Product() {
        super();
    }

    public Product(String pProductName, String pProductDescription, String pProductCategory, String pProductCode, String pProductPrice, String pProductTaxPercent, Integer pProductStock) {
        this.mProductName = pProductName;
        this.mProductDescription = pProductDescription;
        this.mProductCategory = pProductCategory;
        this.mProductCode = pProductCode;
        this.mProductPrice = pProductPrice;
        this.mProductTaxPercent = pProductTaxPercent;
        this.mProductCreationDate=LocalDateTime.now();
        this.mProductStock=pProductStock;
    }

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

    public String getProductTaxPercent() {
        return mProductTaxPercent;
    }

    public void setProductTaxPercent(String pProductTaxPercent) {
        mProductTaxPercent = pProductTaxPercent;
    }

    public LocalDateTime getProductCreationDate() {
        return mProductCreationDate;
    }

    public void setProductCreationDate(LocalDateTime pProductCreationDate) {
        mProductCreationDate = pProductCreationDate;
    }

    public Integer getProductStock() {
        return mProductStock;
    }

    public void setProductStock(Integer pProductStock) {
        this.mProductStock = pProductStock;
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
