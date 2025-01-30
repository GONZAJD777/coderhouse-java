package com.coderhouse.ProyectoFinal_PrimeraEntrega.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "TicketItem")
@Table(name="tickets_item")
public class TicketItem {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    @Schema(name="mTicketItemId", description = "ID del Item del Ticket, autoincremental")
    @Column(name="ticket_item_id")
    private Long mTicketItemId;

    @Schema(name="mTicketItemProductName", description = "Nombre del producto Facturado")
    @Column(name="ticket_item_product_name")
    private String mTicketItemProductName;

    @Schema(name="mTicketItemProductPrice", description = "Precio del producto Facturado")
    @Column(name="ticket_item_product_price")
    private Float mTicketItemProductPrice;

    @Schema(name="mTicketItemProductTax", description = "Porcentaje del precio aplicado como impuesto del producto Facturado")
    @Column(name="ticket_item_product_tax")
    private Float mTicketItemProductTax;

    @Schema(name="mTicketItemProductQuantity", description = "Cantidad de unidades de producto Facturado")
    @Column(name="ticket_item_product_Quantity")
    private Integer mTicketItemProductQuantity;

    @Schema(name="mTicketItemSubTotal", description = "Subtotal resultado del ticket representado por el precio del producto multiplicado por las unidades del producto")
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
