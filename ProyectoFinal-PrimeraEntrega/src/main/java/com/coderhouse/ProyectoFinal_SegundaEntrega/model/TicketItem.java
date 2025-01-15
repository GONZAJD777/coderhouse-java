package com.coderhouse.ProyectoFinal_SegundaEntrega.model;


import jakarta.persistence.*;

@Entity
@Table(name="tickets_item")
public class TicketItem {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Column(name="ticket_item_id")
    private Long mTicketItemId;

    @Column(name="ticket_item_product_name")
    private String mTicketItemProductName;

    @Column(name="ticket_item_product_price")
    private Float mTicketItemProductPrice;

    @Column(name="ticket_item_product_tax")
    private Float mTicketItemProductTax;

    @Column(name="ticket_item_product_Quantity")
    private Integer mTicketItemProductQuantity;

    @Column(name="ticket_item_sub_total")
    private Float mTicketItemSubTotal;

    public TicketItem() {
        super();
    }

    public Long getmTicketItemId() {
        return mTicketItemId;
    }

    public void setmTicketItemId(Long pTicketItemId) {
        mTicketItemId = pTicketItemId;
    }

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

    @Override
    public String toString() {
        return "TicketItem{" +
                "mTicketItemId=" + mTicketItemId +
                ", mTicketItemProductName='" + mTicketItemProductName + '\'' +
                ", mTicketItemProductPrice=" + mTicketItemProductPrice +
                ", mTicketItemProductTax=" + mTicketItemProductTax +
                ", mTicketItemProductQuantity=" + mTicketItemProductQuantity +
                ", mTicketItemSubTotal=" + mTicketItemSubTotal +
                '}';
    }
}
