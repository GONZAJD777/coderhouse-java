package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto;

import jakarta.persistence.Column;

public class TicketItemDTO {
    private String mTicketItemProductName;

    private Float mTicketItemProductPrice;

    private Float mTicketItemProductTax;

    private Integer mTicketItemProductQuantity;

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
