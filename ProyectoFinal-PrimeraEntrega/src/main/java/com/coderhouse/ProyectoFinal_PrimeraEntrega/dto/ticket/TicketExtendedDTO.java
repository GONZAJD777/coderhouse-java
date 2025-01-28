package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartDetailDTO;

import java.util.List;

public class TicketExtendedDTO {

    private TicketDTO mTicket;
    private List<CartDetailDTO> notEnoughStockProducts;


    public TicketDTO getmTicket() {
        return mTicket;
    }

    public void setmTicket(TicketDTO pTicket) {
        mTicket = pTicket;
    }

    public List<CartDetailDTO> getNotEnoughStockProducts() {
        return notEnoughStockProducts;
    }

    public void setNotEnoughStockProducts(List<CartDetailDTO> potEnoughStockProducts) {
        notEnoughStockProducts = potEnoughStockProducts;
    }
}
