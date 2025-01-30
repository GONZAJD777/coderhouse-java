package com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.ticket;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.dto.cart.CartDetailDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "TicketExtendedDTO extiende la informacion mostrada por el Ticket permitiendo incluir la informacion de los productos que no pudieron venderse por falta de stock.<br>" +
        "Mostrara el campo notEnoughStockProducts el cual es un listado de CartDetailDTO, el cual contiene ProductDTO con informacion reducida de Product.<br>")
public class TicketExtendedDTO {

    @Schema(name="mTicket", description = "Ticket resultado de la venta de los productos en el carrito del cliente")
    private TicketDTO mTicket;
    @Schema(name="notEnoughStockProducts", description = "Items del carrito del cliente que no pudieron ser vendidos por falta de stock.<br>" +
            "En los items listados, se puede observar la cantidad de productos disponibles en el stock del producto y tambien la cantidad del producto demandado<br>" +
            "dando una vision completa de la situacion de falta de stock.")
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
