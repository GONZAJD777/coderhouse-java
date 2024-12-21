package com.coderhouse.ProyectoFinal_PrimeraEntrega.models;


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

    public Long getTicketItemId() {
        return mTicketItemId;
    }

    public void setTicketItemId(Long pTicketItemId) {
        mTicketItemId = pTicketItemId;
    }

    public String getTicketItemProductName() {
        return mTicketItemProductName;
    }

    public void setTicketItemProductName(String pTicketItemProductName) {
        mTicketItemProductName = pTicketItemProductName;
    }

    public Float getTicketItemProductPrice() {
        return mTicketItemProductPrice;
    }

    public void setTicketItemProductPrice(Float pTicketItemProductPrice) {
        mTicketItemProductPrice = pTicketItemProductPrice;
    }

    public Float getTicketItemProductTax() {
        return mTicketItemProductTax;
    }

    public void setTicketItemProductTax(Float pTicketItemProductTax) {
        mTicketItemProductTax = pTicketItemProductTax;
    }

    public Integer getTicketItemProductQuantity() {
        return mTicketItemProductQuantity;
    }

    public void setTicketItemProductQuantity(Integer pTicketItemProductQuantity) {
        mTicketItemProductQuantity = pTicketItemProductQuantity;
    }

    public Float getTicketItemSubTotal() {
        return mTicketItemSubTotal;
    }

    public void setTicketItemSubTotal(Float pTicketItemSubTotal) {
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
