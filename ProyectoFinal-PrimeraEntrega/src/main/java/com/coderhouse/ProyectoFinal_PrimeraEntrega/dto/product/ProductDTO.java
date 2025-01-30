package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.product;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "ProductDTO excluye la flag IsActiveFlag de la informacion mostrada")
public class ProductDTO {
    @Schema(name="mProductId", description = "ID del Producto, autoincremental")
    private Long mProductId;
    @Schema(name="mProductName", description = "Nombre del producto")
    private String mProductName;
    @Schema(name="mProductDescription", description = "Descripcion del producto")
    private String mProductDescription;
    @Schema(name="mProductCategory", description = "Categoria del producto")
    private String mProductCategory;
    @Schema(name="mProductCode", description = "Codigo unico del producto")
    private String mProductCode;
    @Schema(name="mProductStock", description = "Stock del producto")
    private Integer mProductStock;
    @Schema(name="mProductPrice", description = "Precio del producto")
    private Float mProductPrice;
    @Schema(name="mProductTaxPercent", description = "Porcentaje de impuestos aplicado al producto")
    private Float mProductTaxPercent;
    @Schema(name="mProductCreationDate", description = "Fecha de creacion del producto")
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
