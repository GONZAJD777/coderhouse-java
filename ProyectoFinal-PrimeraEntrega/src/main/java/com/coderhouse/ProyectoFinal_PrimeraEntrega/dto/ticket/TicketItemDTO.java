package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "TicketItem")
public class TicketItemDTO {

    @Schema(name="mTicketItemProductName", description = "Nombre del producto Facturado")
    private String mTicketItemProductName;

    @Schema(name="mTicketItemProductPrice", description = "Precio del producto Facturado")
    private Float mTicketItemProductPrice;

    @Schema(name="mTicketItemProductTax", description = "Porcentaje del precio aplicado como impuesto del producto Facturado")
    private Float mTicketItemProductTax;

    @Schema(name="mTicketItemProductQuantity", description = "Cantidad de unidades de producto Facturado")
    private Integer mTicketItemProductQuantity;

    @Schema(name="mTicketItemSubTotal", description = "Subtotal resultado del ticket representado por el precio del producto multiplicado por las unidades del producto")
    private Float mTicketItemSubTotal;

    public String getmTicketItemProductName() {
        return mTicketItemProductName;
    }

    public void setmTicketItemProductName(String pTicketItemProductName) {
        mTicketItemProductName = pTicketItemProductName;
    }

    public Float getmTicketItemProductPrice() {
        return mTicketItemProductPrice;
    }

    public void setmTicketItemProductPrice(Float pTicketItemProductPrice) {
        mTicketItemProductPrice = pTicketItemProductPrice;
    }

    public Float getmTicketItemProductTax() {
        return mTicketItemProductTax;
    }

    public void setmTicketItemProductTax(Float pTicketItemProductTax) {
        mTicketItemProductTax = pTicketItemProductTax;
    }

    public Integer getmTicketItemProductQuantity() {
        return mTicketItemProductQuantity;
    }

    public void setmTicketItemProductQuantity(Integer pTicketItemProductQuantity) {
        mTicketItemProductQuantity = pTicketItemProductQuantity;
    }

    public Float getmTicketItemSubTotal() {
        return mTicketItemSubTotal;
    }

    public void setmTicketItemSubTotal(Float pTicketItemSubTotal) {
        mTicketItemSubTotal = pTicketItemSubTotal;
    }
}
