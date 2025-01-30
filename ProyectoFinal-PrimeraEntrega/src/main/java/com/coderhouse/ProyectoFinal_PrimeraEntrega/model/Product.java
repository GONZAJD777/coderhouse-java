package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Schema(description = "Producto")
@Table(name="products")
public class Product {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Schema(name="mProductId", description = "ID del Producto, autoincremental")
    @Column(name="product_id")
    private Long mProductId;

    @Schema(name="mProductName", description = "Nombre del producto")
    @Column(name="product_name")
    private String mProductName;

    @Schema(name="mProductDescription", description = "Descripcion del producto")
    @Column(name="product_description")
    private String mProductDescription;

    @Schema(name="mProductCategory", description = "Categoria del producto")
    @Column(name="product_category")
    private String mProductCategory;

    @Schema(name="mProductCode", description = "Codigo unico del producto")
    @Column(name="product_code", nullable = false)
    private String mProductCode;

    @Schema(name="mProductStock", description = "Stock del producto")
    @Column(name="product_stock")
    private Integer mProductStock;

    @Schema(name="mProductPrice", description = "Precio del producto")
    @Column(name="product_price")
    private Float mProductPrice;

    @Schema(name="mProductTaxPercent", description = "Porcentaje de impuestos aplicado al producto")
    @Column(name="product_tax_percent")
    private Float mProductTaxPercent;

    @Schema(name="mProductCreationDate", description = "Fecha de creacion del producto")
    @Column(name="product_creation_date")
    private LocalDateTime mProductCreationDate;

    @Schema(name="mIsActiveFlag", description = "Flag que indica si el product esta activo, 'false' indica eliminacion")
    @Column(name="product_is_active_flag",nullable = false)
    private Boolean mIsActiveFlag;


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
        this.mIsActiveFlag=true;
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

    public Boolean getmIsActiveFlag() {
        return mIsActiveFlag;
    }

    public void setmIsActiveFlag(Boolean pIsActiveFlag) {
        mIsActiveFlag = pIsActiveFlag;
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
                ", mProductPrice=" + mProductPrice +
                ", mProductTaxPercent=" + mProductTaxPercent +
                ", mProductCreationDate=" + mProductCreationDate +
                ", mIsActiveFlag=" + mIsActiveFlag +
                '}';
    }
}
