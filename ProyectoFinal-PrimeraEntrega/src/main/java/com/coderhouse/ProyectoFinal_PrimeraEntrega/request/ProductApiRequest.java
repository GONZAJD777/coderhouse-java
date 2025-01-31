package com.coderhouse.ProyectoFinal_PrimeraEntrega.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request model for creating or updating a product")
public class ProductApiRequest {

    @Schema(name= "mProductName" ,description = "Nombre del producto", example = "Laptop")
    private String mProductName;

    @Schema(name= "mProductDescription" ,description = "Descripción del producto", example = "Laptop de alta gama con 16GB de RAM")
    private String mProductDescription;

    @Schema(name= "mProductCategory" ,description = "Categoría del producto", example = "Electrónica")
    private String mProductCategory;

    @Schema(name= "mProductCode" ,description = "Código del producto", example = "LP1234")
    private String mProductCode;

    @Schema(name= "mProductStock" ,description = "Stock del producto", example = "50")
    private int mProductStock;

    @Schema(name= "mProductPrice" ,description = "Precio del producto", example = "999.99")
    private double mProductPrice;

    @Schema(name= "mProductTaxPercent" ,description = "Porcentaje de impuestos del producto", example = "15")
    private double mProductTaxPercent;

    // Constructor sin argumentos
    public ProductApiRequest() {}

    // Getters y setters
    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getmProductDescription() {
        return mProductDescription;
    }

    public void setmProductDescription(String mProductDescription) {
        this.mProductDescription = mProductDescription;
    }

    public String getmProductCategory() {
        return mProductCategory;
    }

    public void setmProductCategory(String mProductCategory) {
        this.mProductCategory = mProductCategory;
    }

    public String getmProductCode() {
        return mProductCode;
    }

    public void setmProductCode(String mProductCode) {
        this.mProductCode = mProductCode;
    }

    public int getmProductStock() {
        return mProductStock;
    }

    public void setmProductStock(int mProductStock) {
        this.mProductStock = mProductStock;
    }

    public double getmProductPrice() {
        return mProductPrice;
    }

    public void setmProductPrice(double mProductPrice) {
        this.mProductPrice = mProductPrice;
    }

    public double getmProductTaxPercent() {
        return mProductTaxPercent;
    }

    public void setmProductTaxPercent(double mProductTaxPercent) {
        this.mProductTaxPercent = mProductTaxPercent;
    }
}

